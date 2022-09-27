package com.cidenet.project.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.http.HttpStatus;

@JsonSerialize
@Data
public class CreateResponseOk implements CreateResponse{

    private String message;
    public CreateResponseOk(String message){
        this.message=message;
    }
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.OK;
    }

}
