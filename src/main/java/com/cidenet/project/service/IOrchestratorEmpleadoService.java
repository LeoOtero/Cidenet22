package com.cidenet.project.service;

import com.cidenet.project.exception.ApiException;
import com.cidenet.project.model.request.CreateEmpleadoRequest;
import com.cidenet.project.model.request.UpdateEmpleadoRequest;
import com.cidenet.project.model.response.CreateResponse;
import com.cidenet.project.model.response.EmpleadosResponseOk;
import com.cidenet.project.model.response.UpdateResponse;

import java.sql.SQLException;

public interface IOrchestratorEmpleadoService {
    CreateResponse crearEmpleadoFullData(CreateEmpleadoRequest req) throws ApiException;

    UpdateResponse updateEmpleado(UpdateEmpleadoRequest req) throws ApiException;

    EmpleadosResponseOk retrieveAllEmployees() throws ApiException;
}
