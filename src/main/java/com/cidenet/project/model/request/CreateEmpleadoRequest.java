package com.cidenet.project.model.request;

import com.cidenet.project.util.ApiConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class CreateEmpleadoRequest {

    @NotNull(message = "primer_apellido " + ApiConstants.NOT_NULL_NOR_EMPTY)
    @Pattern(regexp = "^((?=[A-Z])(?![_\\\\\\\\\\-]).){1,20}+$", message = ApiConstants.INVALID_FORMAT)
    @JsonProperty("primer_apellido")
    private String primerApellido;

    @NotNull(message = "segundo_apellido " + ApiConstants.NOT_NULL_NOR_EMPTY)
    @Pattern(regexp = "^((?=[A-Z])(?![_\\\\\\\\\\-]).){1,20}+$", message = ApiConstants.INVALID_FORMAT)
    @JsonProperty("segundo_apellido")
    private String segundoApellido;

    @NotNull(message = "primer_nombre " + ApiConstants.NOT_NULL_NOR_EMPTY)
    @Pattern(regexp = "^((?=[A-Z])(?![_\\\\\\\\\\-]).){1,20}+$", message = ApiConstants.INVALID_FORMAT)
    @JsonProperty("primer_nombre")
    private String primerNombre;

    @NotNull(message = "segundo_nombre " + ApiConstants.NOT_NULL_NOR_EMPTY)
    @Pattern(regexp = "^((?=[A-Z])(?![_\\\\\\\\\\-]).){1,20}+$", message = ApiConstants.INVALID_FORMAT)
    @JsonProperty("segundo_nombre")
    private String segundoNombre;

    @NotNull(message = "otros_nombres " + ApiConstants.NOT_NULL_NOR_EMPTY)
    @Pattern(regexp = "^((?=[A-Z ])(?![_\\\\\\\\\\-]).){1,50}+$", message = ApiConstants.INVALID_FORMAT)
    @JsonProperty("otros_nombres")
    private String otrosNombres;

    @NotNull(message = "pais_de_empleo " + ApiConstants.NOT_NULL_NOR_EMPTY)
    @Pattern(regexp = "^(COLOMBIA|ESTADOS UNIDOS)$", message = ApiConstants.INVALID_COUNTRY)
    @JsonProperty("pais_de_empleo")
    private String paisDeEmpleo;

    @NotNull(message = "tipo_de_indentificacion " + ApiConstants.NOT_NULL_NOR_EMPTY)
    @Pattern(regexp = "^(CÉDULA DE CIUDADANÍA|CÉDULA DE EXTRANJERÍA|PASAPORTE|PERMISO ESPECIAL)$", message = ApiConstants.INVALID_ID_TYPE)
    @JsonProperty("tipo_de_indentificacion")
    private String TipoIdentificacion;

    @NotNull(message = "nro_de_identificacion " + ApiConstants.NOT_NULL_NOR_EMPTY)
    @Pattern(regexp = "^[[A-Z]+[0-9]+[a-z-\\-]]{3,20}+$", message = ApiConstants.IDENTIFICATION_NUMBER)
    @JsonProperty("nro_de_identificacion")
    private String nroIdentificacion;

    @NotNull(message = "fecha_de_ingreso " + ApiConstants.NOT_NULL_NOR_EMPTY)
    @Pattern(regexp = "(?:(?:(?:0[1-9]|1\\d|2[0-8])\\/(?:0[1-9]|1[0-2])|(?:29|30)\\/(?:0[13-9]|1[0-2])" +
            "|31\\/(?:0[13578]|1[02]))\\/[1-9]\\d{3}|29\\/02(?:\\/[1-9]\\d(?:0[48]|[2468][048]|[13579][26])" +
            "|(?:[2468][048]|[13579][26])00))", message = ApiConstants.INVALID_ADMISSION_DATE)
    @JsonProperty("fecha_de_ingreso")
    private String fechaDeIngreso;

    @NotNull(message = "area " + ApiConstants.NOT_NULL_NOR_EMPTY)
    @Pattern(regexp = "^(ADMINISTRACION|FINANCIERA|COMPRAS|INFRAESTRUCTURA|" +
            "OPERACION|TALENTO HUMANO|SERVICIOS VARIOS)$", message = ApiConstants.IDENTIFICATION_NUMBER)
    @JsonProperty("area")
    private String area;
}
