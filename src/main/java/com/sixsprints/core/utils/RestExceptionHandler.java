
package com.sixsprints.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sixsprints.core.exception.BaseException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = { BaseException.class })
  protected ResponseEntity<RestResponse<?>> handleBaseException(BaseException ex, WebRequest request) {
    log.error(ex.getMessage());
    return RestUtil.errorResponse(ex.getMessage(), ex.getHttpStatus());
  }

  @ExceptionHandler(value = { Exception.class })
  protected ResponseEntity<RestResponse<?>> handleUnknownException(Exception ex, WebRequest request) {
    log.error(ex.getMessage(), ex);
    return RestUtil.errorResponse(BaseException.DEFAULT_MESSAGE, BaseException.DEFAULT_HTTP_STATUS);
  }

  @ExceptionHandler(value = { IllegalArgumentException.class })
  protected ResponseEntity<RestResponse<?>> handleIllegalArgumentException(IllegalArgumentException ex,
    WebRequest request) {
    log.error(ex.getMessage());
    return RestUtil.errorResponse(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
    HttpStatus status, WebRequest request) {
    String violation = convertConstraintViolation(ex);
    log.error(violation);
    return new ResponseEntity<Object>(new RestResponse<>(Boolean.FALSE, violation, null), HttpStatus.BAD_REQUEST);
  }

  private String convertConstraintViolation(MethodArgumentNotValidException ex) {
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    List<String> errorMessages = new ArrayList<String>();
    for (FieldError c : fieldErrors) {
      errorMessages.add(c.getField() + " " + c.getDefaultMessage());
    }
    return errorMessages.toString();
  }
}
