package com.cidenet.project.util;

import com.cidenet.project.entity.EmpleadoEntity;
import com.cidenet.project.entity.InfoEmpleoActualEntity;
import com.cidenet.project.model.request.UpdateEmpleadoRequest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Validations {



    /*
Suponiendo que el input no tiene validaciones,
se genera este codigo para no limitar la pantalla edicion del usuario
 */
    public static EmpleadoEntity validateEmployee(UpdateEmpleadoRequest emp, EmpleadoEntity empEntity){
        if(emp.getPrimerNombre() != null && emp.getPrimerNombre().matches("^((?=[A-Z])(?![_\\\\\\\\\\-]).){1,20}+$"))
            empEntity.setPrimerNombre(emp.getPrimerNombre());
        if(emp.getSegundoNombre() != null && emp.getSegundoNombre().matches("^((?=[A-Z])(?![_\\\\\\\\\\-]).){1,20}+$"))
            empEntity.setSegundoNombre(emp.getSegundoNombre());
        if(emp.getOtrosNombres() != null && emp.getOtrosNombres().matches("^((?=[A-Z ])(?![_\\\\\\\\\\-]).){1,50}+$"))
            empEntity.setOtrosNombres(emp.getOtrosNombres());
        if(emp.getPrimerApellido() != null && emp.getPrimerApellido().matches("^((?=[A-Z])(?![_\\\\\\\\\\-]).){1,20}+$"))
            empEntity.setPrimerApellido(emp.getPrimerApellido());
        if(emp.getSegundoApellido() != null && emp.getSegundoApellido().matches("^((?=[A-Z])(?![_\\\\\\\\\\-]).){1,20}+$"))
            empEntity.setSegundoApellido(emp.getSegundoApellido());
        if(emp.getNroIdentificacion() != null && emp.getNroIdentificacion().matches("^[[A-Z]+[0-9]+[a-z-\\-]]{3,20}+$"))
            empEntity.setNroIdentificacion(emp.getNroIdentificacion());
        if(emp.getTipoIdentificacion() != null && emp.getTipoIdentificacion().matches("^(CÉDULA DE CIUDADANÍA|CÉDULA DE EXTRANJERÍA|PASAPORTE|PERMISO ESPECIAL)$"))
            empEntity.setTipoIdentificacion(emp.getTipoIdentificacion());
        return empEntity;
    }

    public static InfoEmpleoActualEntity validateInfoEmpleoActual(UpdateEmpleadoRequest emp, InfoEmpleoActualEntity info) {

        if(emp.getPaisDeEmpleo()!= null && info.getPaisDeEmpleo().matches("^(COLOMBIA|ESTADOS UNIDOS)$"))
            info.setPaisDeEmpleo(emp.getPaisDeEmpleo());
        if(emp.getArea()!=null && emp.getArea().matches("^(ADMINISTRACION|FINANCIERA|COMPRAS|INFRAESTRUCTURA|" +
                "OPERACION|TALENTO HUMANO|SERVICIOS VARIOS)$"))
            info.setArea(emp.getArea());

    return info;
    }

    public static int validateCorreo(List<String> correos){
        int numMayor = 0;
        if(correos.isEmpty())
            return numMayor;
        for (String a : correos){
            String[] arr1 = a.split("@");
            String[] arr2 = arr1[0].split("\\.");
            if(arr2.length>2 && numMayor<Integer.valueOf(arr2[2]))
                numMayor = Integer.valueOf(arr2[2]);
        }
        return numMayor+1;
    }




}
