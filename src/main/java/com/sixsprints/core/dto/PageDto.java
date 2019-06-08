
package com.sixsprints.core.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageDto<T> implements Serializable {

  private static final long serialVersionUID = 4762156998065006126L;

  private Integer currentPage;

  private Integer currentPageSize;

  private Integer totalPages;

  private Long totalElements;

  private List<T> content = new ArrayList<T>();

  public PageDto() {
    super();
  }

  public PageDto(Integer currentPage, Integer currentPageSize, Integer totalPages, Long totalElements,
      List<T> content) {
    super();
    this.currentPage = currentPage;
    this.currentPageSize = currentPageSize;
    this.totalPages = totalPages;
    this.totalElements = totalElements;
    this.content = content;
  }

  public Integer getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }

  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public Long getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(Long totalElements) {
    this.totalElements = totalElements;
  }

  public List<T> getContent() {
    return content;
  }

  public void setContent(List<T> content) {
    this.content = content;
  }

  public Integer getCurrentPageSize() {
    return currentPageSize;
  }

  public void setCurrentPageSize(Integer currentPageSize) {
    this.currentPageSize = currentPageSize;
  }

  @Override
  public String toString() {
    return "PageDto [currentPage=" + currentPage + ", currentPageSize=" + currentPageSize + ", totalPages=" + totalPages
        + ", totalElements=" + totalElements + ", content=" + content + "]";
  }

}
