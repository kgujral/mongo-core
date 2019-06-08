
package com.sixsprints.core.utils;

public class RestResponse<T> {

  private String errorMessage;

  private Boolean status;

  private T data;

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public RestResponse() {
  }

  public RestResponse(T data) {
    this(Boolean.TRUE, null, data);
  }

  public RestResponse(Boolean status, String errorMessage, T data) {
    this.status = status;
    this.errorMessage = errorMessage;
    this.data = data;
  }

}
