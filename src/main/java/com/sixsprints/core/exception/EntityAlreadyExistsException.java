
package com.sixsprints.core.exception;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends BaseException {

  private static final long serialVersionUID = 3642770990168988049L;

  private static final String DEFAULT_MESSAGE = "Already exists !";

  private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.CONFLICT;

  public EntityAlreadyExistsException() {
    this(DEFAULT_HTTP_STATUS, DEFAULT_MESSAGE);
  }

  public EntityAlreadyExistsException(HttpStatus httpStatus, String message) {
    super(httpStatus, DEFAULT_HTTP_STATUS, message, DEFAULT_MESSAGE);
  }

  public EntityAlreadyExistsException(String message) {
    this(DEFAULT_HTTP_STATUS, message);
  }

}
