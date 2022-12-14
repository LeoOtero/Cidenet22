package com.cidenet.project.controller;

import com.cidenet.project.exception.ApiException;
import com.cidenet.project.model.request.CreateEmpleadoRequest;
import com.cidenet.project.model.request.UpdateEmpleadoRequest;
import com.cidenet.project.model.response.CreateResponse;
import com.cidenet.project.model.response.EmpleadosResponseOk;
import com.cidenet.project.model.response.UpdateResponse;
import com.cidenet.project.service.IOrchestratorEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.SQLException;

@RestController
public class Controller {

    @Autowired
    IOrchestratorEmpleadoService orchestratorEmpleadoService;

    @RequestMapping(value = "/read_empleados", method = RequestMethod.GET)
    public ResponseEntity<Object> retrieveEmployees() throws ApiException{
        EmpleadosResponseOk empleadosResponseOk = orchestratorEmpleadoService.retrieveAllEmployees();
        return new ResponseEntity<>(empleadosResponseOk, empleadosResponseOk.getHttpStatus());
    }

    @RequestMapping(value = "/create_empleado", method = RequestMethod.POST)
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody CreateEmpleadoRequest request) throws ApiException {
        CreateResponse createResponse = orchestratorEmpleadoService.crearEmpleadoFullData(request);
        return new ResponseEntity<>(createResponse, createResponse.getHttpStatus());
    }

    @RequestMapping(value = "/update_empleado", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateEmployees(@Valid @RequestBody UpdateEmpleadoRequest request) throws ApiException {
        UpdateResponse updateResponse = orchestratorEmpleadoService.updateEmpleado(request);
        return new ResponseEntity<>(updateResponse, updateResponse.getHttpStatus());
    }
}
