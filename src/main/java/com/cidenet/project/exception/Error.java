package com.cidenet.project.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
public class Error implements Serializable {

  private String status;
  private String title;

  public static final String PREFIX = "ALERTS-";

  public Error(HttpStatus status, String code, String title) {
    this.status = getStatusAsString(status);
    this.title = title;
  }

  public Error(HttpStatus status, String title) {
    this(status, getStatusAsString(status), title);
  }

  private static String getStatusAsString(HttpStatus status) {
    return String.valueOf(status.value());
  }


}
