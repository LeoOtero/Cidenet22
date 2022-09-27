package com.cidenet.project.model.response;

import org.springframework.http.HttpStatus;

public class CreateResponseError extends CommonErrorResponse {

    public CreateResponseError(HttpStatus httpStatus, String errorMessage){
        super(httpStatus, errorMessage);
    }

}