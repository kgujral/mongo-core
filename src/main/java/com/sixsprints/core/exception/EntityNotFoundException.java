
package com.sixsprints.core.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BaseException {

  private static final long serialVersionUID = 1455288609270613866L;

  private static final String DEFAULT_MESSAGE = "Entity not found !";

  private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.NOT_FOUND;

  public EntityNotFoundException() {
    this(DEFAULT_HTTP_STATUS, DEFAULT_MESSAGE);
  }

  public EntityNotFoundException(HttpStatus httpStatus, String message) {
    super(httpStatus, DEFAULT_HTTP_STATUS, message, DEFAULT_MESSAGE);
  }

  public EntityNotFoundException(String message) {
    this(DEFAULT_HTTP_STATUS, message);
  }

}
