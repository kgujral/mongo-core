
package com.sixsprints.core.generic;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCursor;
import com.sixsprints.core.domain.AbstractMongoEntity;
import com.sixsprints.core.domain.CustomSequences;
import com.sixsprints.core.dto.PageDto;
import com.sixsprints.core.exception.BaseException;
import com.sixsprints.core.exception.EntityNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractService<T extends AbstractMongoEntity> implements GenericService<T> {

  @Autowired
  private MongoOperations mongo;

  @Autowired
  protected MongoTemplate mongoTemplate;

  protected int getNextSequence(String seqName) {
    CustomSequences counter = mongo.findAndModify(query(where("_id").is(seqName)), new Update().inc("seq", 1),
        options().returnNew(true).upsert(true), CustomSequences.class);
    return counter.getSeq();
  }

  @Override
  public T findOne(String id) throws EntityNotFoundException {
    if (id == null) {
      throw notFoundException("null");
    }
    Optional<T> entity = repository().findById(id);
    if (!entity.isPresent()) {
      throw notFoundException(id);
    }
    return entity.get();
  }

  @Override
  public T save(T domain) {
    if (StringUtils.isEmpty(domain.getId()) && StringUtils.isEmpty(domain.getSlug())) {
      if (prefix(domain) != null) {
        domain.setSlug(prefix(domain) + getNextSequence(collection(domain)));
      }
    }
    return repository().save(domain);
  }

  @Override
  public List<T> save(List<T> domains) {
    return repository().saveAll(domains);
  }

  @Override
  public Page<T> findAll(Pageable page) {
    Page<T> all = repository().findAll(page);
    return all;
  }

  @Override
  public Page<T> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<T> all = repository().findAll(pageable);
    return all;
  }

  @Override
  public List<T> findAll() {
    List<T> all = repository().findAll();
    return all;
  }

  @Override
  public void delete(T entity) {
    repository().delete(entity);
    log.debug("Deleted: " + entity);
  }

  @Override
  public void delete(String id) throws EntityNotFoundException {
    T entity = findOne(id);
    delete(entity);
  }

  @Override
  public T update(String id, T domain) throws EntityNotFoundException {
    log.debug("Updating id: " + id + " with " + domain);
    T fromDb = findOne(id);
    domain.copyEntityFrom(fromDb);
    return save(domain);
  }

  @Override
  public List<T> findAllActive() {
    return repository().findAllByActiveTrue();
  }

  @Override
  public Page<T> findAllActive(Pageable pageable) {
    return repository().findAllByActiveTrue(pageable);
  }

  @Override
  public Page<T> findAllActive(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return findAllActive(pageable);
  }

  @Override
  public Page<T> findAllLike(T entity, Pageable page) {
    Example<T> example = Example.of(entity);
    return repository().findAll(example, page);
  }

  @Override
  public List<T> findAllLike(T entity) {
    Example<T> example = Example.of(entity);
    return repository().findAll(example);
  }

  @Override
  public T findOneLike(T entity) {
    Example<T> example = Example.of(entity);
    Optional<T> db = repository().findOne(example);
    if (db.isPresent()) {
      return db.get();
    }
    return null;
  }

  @Override
  public PageDto<T> toPageDto(Page<T> page) {
    PageDto<T> pageDto = new PageDto<>();
    pageDto.setCurrentPageSize(page.getNumberOfElements());
    pageDto.setCurrentPage(page.getNumber());
    pageDto.setTotalElements(page.getTotalElements());
    pageDto.setTotalPages(page.getTotalPages());
    pageDto.setContent(page.getContent());
    return pageDto;
  }

  protected EntityNotFoundException notFoundException(String id) throws EntityNotFoundException {
    return notFoundException("id", id);
  }

  protected EntityNotFoundException notFoundException(String key, String id) throws EntityNotFoundException {
    throw new EntityNotFoundException("Entity not found with " + key + " = " + id);
  }

  protected abstract GenericRepository<T> repository();

  protected String prefix(T domain) {
    return null;
  }

  protected String collection(T domain) {
    return null;
  }

  public void validatePageAndSize(Integer pageNumber, Integer pageSize) throws BaseException {
    if ((pageNumber == null) || (pageSize == null) || (pageNumber < 0) || (pageSize < 0)) {
      throw new BaseException(HttpStatus.BAD_REQUEST, "PageNumber or PageSize not valid");
    }
  }

  @Override
  public Long countAllLike(T entity) {
    Example<T> example = Example.of(entity);
    return repository().count(example);
  }

  @Override
  public T findBySlug(String slug) throws EntityNotFoundException {
    T entity = repository().findBySlug(slug);
    if (entity == null) {
      throw notFoundException("slug", slug);
    }
    return entity;
  }

  @Override
  public List<String> distinctColumnValues(String collection, String column) {

    Query query = new Query();

    DistinctIterable<String> iterable = mongoTemplate.getCollection(collection).distinct(column, query.getQueryObject(),
        String.class);
    MongoCursor<String> cursor = iterable.iterator();
    List<String> list = new ArrayList<>();
    while (cursor.hasNext()) {
      list.add(cursor.next());
    }
    return list;
  }

}
