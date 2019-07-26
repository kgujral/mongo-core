
package com.sixsprints.core.exception;

import org.springframework.http.HttpStatus;

public class EntityInvalidException extends BaseException {

  private static final long serialVersionUID = 1455288609270613866L;

  private static final String DEFAULT_MESSAGE = "Entity is invalid!";

  private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.BAD_REQUEST;

  public EntityInvalidException() {
    this(DEFAULT_HTTP_STATUS, DEFAULT_MESSAGE, null);
  }

  public EntityInvalidException(HttpStatus httpStatus, String message, String[] args) {
    super(httpStatus, DEFAULT_HTTP_STATUS, message, DEFAULT_MESSAGE, args);
  }

  public EntityInvalidException(String message) {
    this(DEFAULT_HTTP_STATUS, message, null);
  }

  public EntityInvalidException(String message, String[] args) {
    this(DEFAULT_HTTP_STATUS, message, args);
  }

}
