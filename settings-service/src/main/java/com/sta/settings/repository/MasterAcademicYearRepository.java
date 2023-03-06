package com.sta.settings.repository;

import com.sta.settings.entities.MasterAcademicYearEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

@Repository
public interface MasterAcademicYearRepository extends JpaRepository<MasterAcademicYearEntity, String>, JpaSpecificationExecutor {


    MasterAcademicYearEntity findByName(String name);

}
