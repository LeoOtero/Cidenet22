package com.cidenet.project.model.response;

import com.cidenet.project.model.dto.EmpleadoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class EmpleadosResponseOk implements GenericResponse{

    @JsonProperty("Lista_De_Empleados")
    private List<EmpleadoDTO> empleadoList;

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.OK;
    }

}
