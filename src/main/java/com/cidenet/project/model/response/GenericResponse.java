package com.cidenet.project.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public interface GenericResponse {

    @JsonIgnore
    String toString();
    @JsonIgnore
    HttpStatus getHttpStatus();
}
