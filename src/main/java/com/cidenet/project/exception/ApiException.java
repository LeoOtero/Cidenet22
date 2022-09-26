package com.cidenet.project.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

@JsonSerialize(using = CustomApiExceptionSerializer.class)
public class ApiException extends Exception {

    public static final String TECHNICAL_ERROR = "Technical error.";
    private static final long serialVersionUID = 1L;
    private final ErrorResponse response;

    public ApiException() {
        this(HttpStatus.INTERNAL_SERVER_ERROR, TECHNICAL_ERROR);
    }



    public ApiException(HttpStatus status, String code, String msg) {
        super(msg);
        this.response = new ErrorResponse(status, msg);
    }

    public ApiException(HttpStatus status, String msg) {
        this(status, String.valueOf(status.value()), msg);
    }

    public ApiException(String[] values) {
        this(HttpStatus.valueOf(Integer.valueOf(values[0])), values[1], values[2]);
    }

}