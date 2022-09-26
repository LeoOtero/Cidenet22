package com.cidenet.project.repository;

import com.cidenet.project.entity.EmpleadoEntity;
import com.cidenet.project.model.request.CreateEmpleadoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmpleadosRepository extends JpaRepository<EmpleadoEntity,Long> {

    List<EmpleadoEntity> findAll();


    EmpleadoEntity findByNroIdentificacionAndTipoIdentificacion(String nro, String tipo);
}
