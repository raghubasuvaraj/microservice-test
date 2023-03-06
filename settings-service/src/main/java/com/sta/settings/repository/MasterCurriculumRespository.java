package com.sta.settings.repository;

import com.sta.settings.entities.MasterCurriculumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterCurriculumRespository extends JpaRepository<MasterCurriculumEntity,String>, JpaSpecificationExecutor<MasterCurriculumEntity> {

}
