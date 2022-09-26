package com.cidenet.project.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
public class EmpleadoDTO {

    @JsonProperty("nro_de_identificacion")
    private String nroIdentificacion;
    @JsonProperty("tipo_de_indentificacion")
    private String tipoIdentificacion;
    @JsonProperty("primer_apellido")
    private String primerApellido;
    @JsonProperty("segundo_apellido")
    private String segundoApellido;
    @JsonProperty("primer_nombre")
    private String primerNombre;
    @JsonProperty("segundo_nombre")
    private String segundoNombre;
    @JsonProperty("otros_nombres")
    private String otrosNombres;
    @JsonProperty("correo_electronico")
    private String correoElectroninco;
    @JsonProperty("pais_de_empleo")
    private String paisDeEmpleo;
    @JsonProperty("fecha_de_ingreso")
    private String fechaDeIngreso;
    @JsonProperty("fecha_de_edicion")
    private String fechaDeEdicion;
    @JsonProperty("area")
    private String area;
    @JsonProperty("estado")
    private final String estado = "Activo";
    @JsonProperty("fecha_y_hora_de_registro")
    private String fechaYHoraDeRegistro;

}
