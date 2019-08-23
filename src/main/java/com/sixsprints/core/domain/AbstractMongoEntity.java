package com.sixsprints.core.domain;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Document
@Data
@EqualsAndHashCode
public class AbstractMongoEntity {

  @Id
  protected String id;

  @CreatedDate
  protected Date dateCreated;

  @LastModifiedDate
  protected Date dateModified;

  @Indexed(unique = true, sparse = true)
  protected String slug;

  @Indexed
  protected Boolean active = Boolean.TRUE;

  @CreatedBy
  private String createdBy;

  @LastModifiedBy
  private String lastModifiedBy;

  public void copyEntityFrom(AbstractMongoEntity source) {
    this.id = source.id;
    this.active = source.active;
    this.dateCreated = source.dateCreated;
    this.dateModified = source.dateModified;
    this.slug = source.slug;
    this.createdBy = source.createdBy;
    this.lastModifiedBy = source.lastModifiedBy;
  }

}