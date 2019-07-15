
package com.sixsprints.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class BaseException extends Exception {

  private static final long serialVersionUID = 7589898601904574752L;

  public static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

  public static final String DEFAULT_MESSAGE = "Something Bad Happened !";

  private HttpStatus httpStatus;

  private String message;

  private String[] arguments;

  public String[] getArguments() {
    return arguments;
  }

  public void setArguments(String[] arguments) {
    this.arguments = arguments;
  }

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

  public BaseException() {
    this(DEFAULT_HTTP_STATUS, DEFAULT_MESSAGE);
  }

  public BaseException(String message) {
    this(DEFAULT_HTTP_STATUS, message);
  }

  public BaseException(HttpStatus httpStatus, String message) {
    this(httpStatus, message, null);
  }

  public BaseException(String message, String[] arguments) {
    this(DEFAULT_HTTP_STATUS, message, arguments);
  }

  public BaseException(HttpStatus httpStatus, String message, String[] arguments) {
    this(httpStatus, DEFAULT_HTTP_STATUS, message, DEFAULT_MESSAGE, arguments);
  }

  public BaseException(HttpStatus httpStatus, HttpStatus defaultStatus, String message, String defaultMessage,
      String[] args) {
    super();
    this.httpStatus = httpStatus == null ? defaultStatus : httpStatus;
    this.message = StringUtils.isEmpty(message) ? defaultMessage : message;
    this.arguments = args;
  }

}
