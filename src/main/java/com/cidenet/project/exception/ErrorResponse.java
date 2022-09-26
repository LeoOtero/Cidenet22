package com.cidenet.project.exception;

import com.cidenet.project.model.response.GenericResponse;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorResponse implements Serializable, GenericResponse {

  private List<Error> errors;

  public ErrorResponse() {
    if (errors == null) {
      errors = new ArrayList<>();
    }
  }

  public ErrorResponse(HttpStatus status, String code, String title) {
    this();
    addError(status, code, title);
  }

  public ErrorResponse(HttpStatus status) {
    this(status, String.valueOf(status.value()), status.getReasonPhrase());
  }

  public ErrorResponse(HttpStatus status, String title) {
    this(status, String.valueOf(status.value()), title);
  }

  private void addError(Error error) {
    errors.add(error);
  }

  public void addError(HttpStatus status, String title) {
    addError(new Error(status, title));
  }

  private void addError(HttpStatus status, String code, String title) {
    addError(new Error(status, code, title));
  }

  public String getStatus(int index) {
    return errors.get(index).getStatus();
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.resolve(Integer.valueOf(getStatus(0)));
  }
}
