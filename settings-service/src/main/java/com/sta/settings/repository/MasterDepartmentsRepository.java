package com.sta.settings.repository;

import com.sta.settings.entities.MasterDepartmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterDepartmentsRepository extends JpaRepository<MasterDepartmentsEntity, String>, JpaSpecificationExecutor {
}
