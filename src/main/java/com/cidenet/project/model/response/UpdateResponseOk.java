package com.cidenet.project.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.http.HttpStatus;

@JsonSerialize
@Data
public class UpdateResponseOk implements UpdateResponse{
    private String message;

    public UpdateResponseOk(String message){
        this.message=message;
    }
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.OK;
    }
}
