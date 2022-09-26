package com.cidenet.project.service;

import com.cidenet.project.exception.ApiException;
import com.cidenet.project.model.request.CreateEmpleadoRequest;
import com.cidenet.project.model.request.UpdateEmpleadoRequest;
import com.cidenet.project.model.response.EmpleadosResponseOk;

import java.sql.SQLException;

public interface IOrchestratorEmpleadoService {
    String crearEmpleadoFullData(CreateEmpleadoRequest req) throws ApiException;

    String updateEmpleado(UpdateEmpleadoRequest req) throws ApiException;

    EmpleadosResponseOk retrieveAllEmployees() throws ApiException;
}
