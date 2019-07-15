
package com.sixsprints.core.exception;

import org.springframework.http.HttpStatus;

public class NotAuthorizedException extends BaseException {

  private static final long serialVersionUID = -7565260959261747230L;

  private static final String DEFAULT_MESSAGE = "Not Authorized !";

  private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.UNAUTHORIZED;

  public NotAuthorizedException() {
    this(DEFAULT_HTTP_STATUS, DEFAULT_MESSAGE);
  }

  public NotAuthorizedException(String message) {
    this(DEFAULT_HTTP_STATUS, message);
  }

  public NotAuthorizedException(String message, String[] args) {
    this(DEFAULT_HTTP_STATUS, message, args);
  }

  public NotAuthorizedException(HttpStatus httpStatus, String message) {
    this(httpStatus, message, null);
  }

  public NotAuthorizedException(HttpStatus httpStatus, String message, String[] args) {
    super(httpStatus, DEFAULT_HTTP_STATUS, message, DEFAULT_MESSAGE, args);
  }

}
