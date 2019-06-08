
package com.sixsprints.core.exception;

import org.springframework.http.HttpStatus;

public class NotAuthenticatedException extends BaseException {

  private static final long serialVersionUID = 763801764959412956L;

  private static final String DEFAULT_MESSAGE = "Not authenticated !";

  private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.FORBIDDEN;

  public NotAuthenticatedException() {
    this(DEFAULT_HTTP_STATUS, DEFAULT_MESSAGE);
  }

  public NotAuthenticatedException(HttpStatus httpStatus, String message) {
    super(httpStatus, DEFAULT_HTTP_STATUS, message, DEFAULT_MESSAGE);

  }

  public NotAuthenticatedException(String message) {
    this(DEFAULT_HTTP_STATUS, message);
  }

}
