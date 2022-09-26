package com.cidenet.project.repository;

import com.cidenet.project.entity.InfoEmpleoActualEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoEmpleoActualRepository  extends JpaRepository<InfoEmpleoActualEntity,Long> {
}
