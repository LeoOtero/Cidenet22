package com.cidenet.project.service;

import com.cidenet.project.entity.EmpleadoEntity;
import com.cidenet.project.exception.ApiException;
import com.cidenet.project.model.dto.EmpleadoDTO;
import com.cidenet.project.model.request.CreateEmpleadoRequest;
import com.cidenet.project.model.request.UpdateEmpleadoRequest;

import java.sql.SQLException;
import java.util.List;

public interface IEmpleadoService {
    List<EmpleadoEntity> getEmpleados();

    String createEmpleado(CreateEmpleadoRequest req) throws ApiException;

    String updateEmpleado(UpdateEmpleadoRequest req, EmpleadoEntity empleado);

    EmpleadoEntity findEntityByNroIdAndTipoId(String nroIdentificacion, String tipoIdentificacion);

    List<EmpleadoDTO> retrieveAllEmployeesFormattedDate();
}
