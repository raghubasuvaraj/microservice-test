package com.sta.settings.repository;

import com.sta.settings.entities.InstituteCurriculumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstituteCurriculumRepository extends JpaRepository<InstituteCurriculumEntity,String> , JpaSpecificationExecutor<InstituteCurriculumEntity> {
    Optional<InstituteCurriculumEntity> findByIdAndActive(String instituteCurriculumId, boolean b);

    List<InstituteCurriculumEntity> findByInstituteIdAndActive(String instituteId, boolean b);
}
