package com.cidenet.project.model.response;

import org.springframework.http.HttpStatus;

public class UpdateResponseError extends CommonErrorResponse {

    public UpdateResponseError(HttpStatus httpStatus, String errorMessage){
        super(httpStatus, errorMessage);
    }

}
