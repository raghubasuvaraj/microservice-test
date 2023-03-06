package com.sta.settings.repository;

import com.sta.dc.common.entity.academic.MasterAcademicClassesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterAcademicClassesRepository extends JpaRepository<MasterAcademicClassesEntity, String>, JpaSpecificationExecutor {
}
