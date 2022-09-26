package com.cidenet.project.service.impl;

import com.cidenet.project.entity.EmpleadoEntity;
import com.cidenet.project.entity.InfoEmpleoActualEntity;
import com.cidenet.project.model.dto.EmpleadoDTO;
import com.cidenet.project.model.request.CreateEmpleadoRequest;
import com.cidenet.project.model.request.UpdateEmpleadoRequest;
import com.cidenet.project.repository.EmpleadosRepository;
import com.cidenet.project.service.IEmpleadoService;
import com.cidenet.project.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleadoService implements IEmpleadoService {

    @Autowired
    private Logger logger;

    @Autowired
    private DataSource ds;
    @Autowired
    EmpleadosRepository empleadosRepository;

    @Override
    public List<EmpleadoEntity> getEmpleados() {
        return  empleadosRepository.findAll();
    }

    @Override
    public EmpleadoEntity findEntityByNroIdAndTipoId(String nroIdentificacion, String tipoIdentificacion) {
        return empleadosRepository.findByNroIdentificacionAndTipoIdentificacion(nroIdentificacion, tipoIdentificacion);
    }

    @Transactional
    public String createEmpleado(CreateEmpleadoRequest req) {
        try {
            logger.logMessage(LoggingLevels.INFO, "Creating Employee");
            EmpleadoEntity e = new EmpleadoEntity();
            e.setPrimerNombre(req.getPrimerNombre());
            e.setSegundoNombre(req.getSegundoNombre());
            e.setOtrosNombres(req.getOtrosNombres());
            e.setPrimerApellido(req.getPrimerApellido());
            e.setSegundoApellido(req.getSegundoApellido());
            e.setNroIdentificacion(req.getNroIdentificacion());
            e.setTipoIdentificacion(req.getTipoIdentificacion());
            InfoEmpleoActualEntity i = new InfoEmpleoActualEntity();
            i.setArea(req.getArea());
            List<String> correos = retrieveEmployeeByMail(req.getPrimerNombre() + "." + req.getPrimerApellido(),
                    req.getPaisDeEmpleo());
            Integer numMayor = Validations.validateCorreo(correos);
            if(numMayor>0)
                i.setCorreoElectroninco(req.getPrimerNombre() + "." + req.getPrimerApellido() +
                        "." +numMayor+"@" + (req.getPaisDeEmpleo().equals("COLOMBIA") ? ApiConstants.DOMINIO_COL : ApiConstants.DOMINIO_USA));
            else{
                i.setCorreoElectroninco(req.getPrimerNombre() + "." + req.getPrimerApellido() +
                        "@" + (req.getPaisDeEmpleo().equals("COLOMBIA") ? ApiConstants.DOMINIO_COL : ApiConstants.DOMINIO_USA));
            }
            if (UtilClass.checkDate(req.getFechaDeIngreso()))
                return ApiConstants.DATE_OLDER_MONTH;
            else {
                i.setFechaDeIngreso(UtilClass.setDateAdm(req.getFechaDeIngreso()));
            }
            i.setFechaDeIngreso(Date.valueOf(req.getFechaDeIngreso()));
            i.setPaisDeEmpleo(req.getPaisDeEmpleo());
            i.setFechaYHoraDeRegistro(new java.sql.Date(System.currentTimeMillis()));
            e.setInfoEmpleoActualEntity(i);
            logger.logMessage(LoggingLevels.INFO, "Saving Employee");
            empleadosRepository.save(e);
            // infoEmpleoActualRepository.save(i);
            return ApiConstants.EMPLOYEE_CREATED;
        } catch (Exception e) {
            return (e.getMessage());
        }
    }

    @Override
    public String updateEmpleado(UpdateEmpleadoRequest req, EmpleadoEntity empleado){
        try {
            logger.logMessage(LoggingLevels.INFO, "Cheking employee");
            empleado = Validations.validateEmployee(req, empleado);
            empleado.setInfoEmpleoActualEntity(Validations.validateInfoEmpleoActual(req, empleado.getInfoEmpleoActualEntity()));
            List<String> correos = retrieveEmployeeByMail(req.getPrimerNombre() + "." + req.getPrimerApellido(),
                    req.getPaisDeEmpleo());
            Integer numMayor = Validations.validateCorreo(correos);
            if(numMayor>0)
                empleado.getInfoEmpleoActualEntity().setCorreoElectroninco(req.getPrimerNombre() + "." + req.getPrimerApellido() +
                        "." +numMayor+"@" + (req.getPaisDeEmpleo().equals("COLOMBIA") ? ApiConstants.DOMINIO_COL : ApiConstants.DOMINIO_USA));
            else{
                empleado.getInfoEmpleoActualEntity().setCorreoElectroninco(req.getPrimerNombre() + "." + req.getPrimerApellido() +
                        "@" + (req.getPaisDeEmpleo().equals("COLOMBIA") ? ApiConstants.DOMINIO_COL : ApiConstants.DOMINIO_USA));
            }
            empleado.getInfoEmpleoActualEntity().setFechaDeEdicion(new java.sql.Date(System.currentTimeMillis()));
            logger.logMessage(LoggingLevels.INFO, "Saving employee");
            empleadosRepository.save(empleado);
        }catch(Exception e){
            return ApiConstants.EMPLOYEE_PROBLEM_AT_UPDATE;
        }
        return ApiConstants.EMPLOYEE_SUCCESSFULLY_UPDATE;
    }

    public List<EmpleadoDTO> retrieveAllEmployeesFormattedDate() {

        List<EmpleadoDTO> response = new ArrayList<>();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";

        try {
            sql = "SELECT NroIdentificacion, OtrosNombres, PrimerApellido, PrimerNombre, SegundoApellido, SegundoNombre,\n" +
                    "TipoIdentificacion, Area, CorreoElectroninco, Estado, DATE_FORMAT(FechaDeEdicion, '%d/%m/%Y %H:%i') as FechaDeEdicion,\n" +
                    "DATE_FORMAT(FechaDeIngreso, '%d/%m/%Y %H:%i') as FechaDeIngreso, DATE_FORMAT(FechaYHoraDeRegistro, '%d/%m/%Y %H:%i') as FechaYHoraDeRegistro,\n" +
                    "PaisDeEmpleo FROM empleados INNER JOIN infoempleoactual ON empleados.id = infoempleoactual.id;";
            con = ds.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                EmpleadoDTO emDto = new EmpleadoDTO();
                emDto.setOtrosNombres(rs.getString("OtrosNombres"));
                emDto.setNroIdentificacion(rs.getString("NroIdentificacion"));
                emDto.setPrimerNombre(rs.getString("PrimerNombre"));
                emDto.setSegundoNombre(rs.getString("SegundoNombre"));
                emDto.setPrimerApellido(rs.getString("PrimerApellido"));
                emDto.setSegundoApellido(rs.getString("SegundoApellido"));
                emDto.setTipoIdentificacion(rs.getString("TipoIdentificacion"));
                emDto.setArea(rs.getString("Area"));
                emDto.setPaisDeEmpleo(rs.getString("PaisDeEmpleo"));
                emDto.setFechaDeEdicion(rs.getString("FechaDeEdicion"));
                emDto.setFechaDeIngreso(rs.getString("FechaDeIngreso"));
                emDto.setFechaYHoraDeRegistro(rs.getString("FechaYHoraDeRegistro"));
                emDto.setCorreoElectroninco(rs.getString("CorreoElectroninco"));
                response.add(emDto);
            }} catch (SQLException ex) {
            logger.logMessage(LoggingLevels.ERROR, sql);
        } finally {
            try {

                if (rs != null) {
                    rs.close();
                }

                if (pst != null) {
                    pst.close();
                }

                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                logger.logMessage(LoggingLevels.WARNING, ex.getMessage());
            }
            logger.logMessage(LoggingLevels.INFO, "employees retrieved from DB successfully");
        }
        return response;

    }

    private List<String> retrieveEmployeeByMail(String correo, String paisEmpleo) throws SQLException {

        List<String> response = new ArrayList<>();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";

        try {
            sql = "SELECT CorreoElectroninco FROM cidenet.infoempleoactual WHERE CorreoElectroninco like '" +correo+
                    "%' and PaisDeEmpleo = '" + paisEmpleo + "'";
            con = ds.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                response.add(rs.getString("CorreoElectroninco"));
            }} catch (SQLException ex) {
            logger.logMessage(LoggingLevels.ERROR, sql);
        } finally {
            try {

                if (rs != null) {
                    rs.close();
                }

                if (pst != null) {
                    pst.close();
                }

                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                logger.logMessage(LoggingLevels.WARNING, ex.getMessage());
            }
            logger.logMessage(LoggingLevels.INFO, "mail retrieved from DB successfully");
        }
        return response;

    }

}
