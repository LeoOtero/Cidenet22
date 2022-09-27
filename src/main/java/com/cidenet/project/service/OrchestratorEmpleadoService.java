package com.cidenet.project.service;

import com.cidenet.project.entity.EmpleadoEntity;
import com.cidenet.project.exception.ApiException;
import com.cidenet.project.model.dto.EmpleadoDTO;
import com.cidenet.project.model.request.CreateEmpleadoRequest;
import com.cidenet.project.model.request.UpdateEmpleadoRequest;
import com.cidenet.project.model.response.*;
import com.cidenet.project.util.ApiConstants;
import com.cidenet.project.util.Logger;
import com.cidenet.project.util.LoggingLevels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrchestratorEmpleadoService implements IOrchestratorEmpleadoService {
    @Autowired
    IEmpleadoService empleadoService;

    @Autowired
    private Logger logger;

    @Override
    public CreateResponse crearEmpleadoFullData(CreateEmpleadoRequest req) throws ApiException {
        logger.logMessage(LoggingLevels.INFO, "chequeando usuario en BD:");
        EmpleadoEntity emp = empleadoService.findEntityByNroIdAndTipoId(req.getNroIdentificacion(), req.getTipoIdentificacion());
        if (emp != null) {
            return new CreateResponseError(HttpStatus.BAD_REQUEST, ApiConstants.EMPLOYEE_ALREADY_CREATED);
        }
        logger.logMessage(LoggingLevels.INFO, "creando usuario");
        return new CreateResponseOk(empleadoService.createEmpleado(req));
    }

    @Override
    public UpdateResponse updateEmpleado(UpdateEmpleadoRequest req) throws ApiException {
        logger.logMessage(LoggingLevels.INFO, "chequeando existencia de usuario en BD:");
        EmpleadoEntity emp = empleadoService.findEntityByNroIdAndTipoId(req.getNroIdentificacion(), req.getTipoIdentificacion());
        if (emp == null) {
            return new UpdateResponseError(HttpStatus.NOT_FOUND, ApiConstants.EMPLOYEE_DOESNT_EXIST);
        }
        logger.logMessage(LoggingLevels.INFO, "Actualizando usuario");
        return new UpdateResponseOk(empleadoService.updateEmpleado(req, emp));
    }

    public EmpleadosResponseOk retrieveAllEmployees() throws ApiException {
        List<EmpleadoDTO> entityEmpleadosList = empleadoService.retrieveAllEmployeesFormattedDate();
        if (entityEmpleadosList.isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND, ApiConstants.NO_EMPLOYEE_RETRIEVED);
        EmpleadosResponseOk empleadosResponseOk = new EmpleadosResponseOk();
        empleadosResponseOk.setEmpleadoList(entityEmpleadosList);
        return empleadosResponseOk;
    }

}
