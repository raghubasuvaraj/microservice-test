package com.sta.settings.repository;

import java.util.List;

import com.sta.settings.entities.InstituteDepartmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sta.settings.dto.response.DepartmentsResponse;

@Repository
public interface InstituteDepartmentsRepository extends JpaRepository<InstituteDepartmentsEntity, String> , JpaSpecificationExecutor<InstituteDepartmentsEntity> {

}
