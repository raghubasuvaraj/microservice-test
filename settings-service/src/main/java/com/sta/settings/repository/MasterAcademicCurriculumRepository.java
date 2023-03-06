package com.sta.settings.repository;

import com.sta.settings.entities.MasterAcademicCurriculumEntity;
import com.sta.settings.entities.MasterRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterAcademicCurriculumRepository extends JpaRepository<MasterAcademicCurriculumEntity, String>, JpaSpecificationExecutor {
}
