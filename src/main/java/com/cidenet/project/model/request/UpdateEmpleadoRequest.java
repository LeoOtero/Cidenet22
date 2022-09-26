package com.cidenet.project.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class UpdateEmpleadoRequest {

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

    @JsonProperty("pais_de_empleo")
    private String paisDeEmpleo;

    @JsonProperty("tipo_de_indentificacion")
    private String TipoIdentificacion;

    @JsonProperty("nro_de_identificacion")
    private String nroIdentificacion;

    @JsonProperty("fecha_de_ingreso")
    private String fechaDeIngreso;

    @JsonProperty("area")
    private String area;
}
