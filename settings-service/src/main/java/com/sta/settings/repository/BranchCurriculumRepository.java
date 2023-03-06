package com.sta.settings.repository;

import com.sta.settings.entities.BranchCurriculumEntity;
import com.sta.settings.entities.InstituteCurriculumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchCurriculumRepository extends JpaRepository<BranchCurriculumEntity,String>, JpaSpecificationExecutor<BranchCurriculumEntity> {

    Optional<BranchCurriculumEntity> findByIdAndActive(String branchCurriculumId, boolean b);
}
