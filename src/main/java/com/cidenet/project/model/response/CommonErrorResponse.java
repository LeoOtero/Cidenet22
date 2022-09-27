package com.cidenet.project.model.response;

import com.cidenet.project.exception.ErrorResponse;
import org.springframework.http.HttpStatus;

public class CommonErrorResponse extends ErrorResponse
    implements GenericResponse, UpdateResponse, CreateResponse {

  public CommonErrorResponse(HttpStatus httpStatus, String errorMessage) {
    super(
        httpStatus.equals(HttpStatus.OK) ? HttpStatus.INTERNAL_SERVER_ERROR : httpStatus,
        errorMessage);
  }

  public CommonErrorResponse() {
    super(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public CommonErrorResponse(HttpStatus httpStatus, String code, String title) {
  }
}
