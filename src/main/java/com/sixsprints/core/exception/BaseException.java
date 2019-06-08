
package com.sixsprints.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class BaseException extends Exception {

  private static final long serialVersionUID = 7589898601904574752L;

  public static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

  public static final String DEFAULT_MESSAGE = "Something Bad Happened !";

  private HttpStatus httpStatus;

  private String message;

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public BaseException(HttpStatus httpStatus, String message) {
    super();
    this.httpStatus = httpStatus == null ? DEFAULT_HTTP_STATUS : httpStatus;
    this.message = StringUtils.isEmpty(message) ? DEFAULT_MESSAGE : message;
  }

  public BaseException(HttpStatus httpStatus, HttpStatus defaultStatus, String message, String defaultMessage) {
    super();
    this.httpStatus = httpStatus == null ? defaultStatus : httpStatus;
    this.message = StringUtils.isEmpty(message) ? defaultMessage : message;
  }

  public BaseException(String message) {
    this(DEFAULT_HTTP_STATUS, message);
  }

  public BaseException() {
    this(DEFAULT_HTTP_STATUS, DEFAULT_MESSAGE);
  }

}
