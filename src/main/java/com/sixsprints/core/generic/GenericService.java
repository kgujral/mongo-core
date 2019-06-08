
package com.sixsprints.core.generic;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sixsprints.core.domain.AbstractMongoEntity;
import com.sixsprints.core.dto.PageDto;
import com.sixsprints.core.exception.EntityNotFoundException;

public interface GenericService<T extends AbstractMongoEntity> {

  T findOne(String id) throws EntityNotFoundException;

  T findBySlug(String slug) throws EntityNotFoundException;

  T save(T domain);

  List<T> save(List<T> domains);

  Page<T> findAll(Pageable page);

  Page<T> findAll(int page, int size);

  List<T> findAll();

  Page<T> findAllActive(Pageable page);

  Page<T> findAllActive(int page, int size);

  List<T> findAllActive();

  void delete(String id) throws EntityNotFoundException;

  void delete(T entity);

  T update(String id, T domain) throws EntityNotFoundException;

  Page<T> findAllLike(T example, Pageable page);

  List<T> findAllLike(T example);

  T findOneLike(T example);

  PageDto<T> toPageDto(Page<T> page);

  Long countAllLike(T entity);

}
