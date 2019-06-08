package com.sixsprints.core.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class RestUtil {

  public static <T> ResponseEntity<RestResponse<T>> successResponse(T data, HttpStatus statusCode) {
    return new ResponseEntity<RestResponse<T>>(new RestResponse<>(data), statusCode);
  }

  public static <T> ResponseEntity<RestResponse<T>> successResponse(T data) {
    return successResponse(data, HttpStatus.OK);
  }

  public static <T> ResponseEntity<RestResponse<?>> errorResponse(String errorMessage, HttpStatus statusCode) {
    return new ResponseEntity<RestResponse<?>>(new RestResponse<T>(Boolean.FALSE, errorMessage, null), statusCode);
  }
}
