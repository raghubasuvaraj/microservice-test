package com.sta.settings.repository;

import com.sta.settings.entities.InstituteAcademicYearEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcademicYearRepository extends JpaRepository<InstituteAcademicYearEntity, String>, JpaSpecificationExecutor {

    @Query(value = "select * from mse_institute_academic_year a " +
            "inner join mse_master_academic_years m on a.year_id = m.id where a.id = ?1 and a.is_active = 1", nativeQuery = true)
    InstituteAcademicYearEntity findByIdWithName(String id);

    @Query(value = "select * from mse_institute_academic_year a " +
            "inner join mse_master_academic_years m on a.year_id = m.id where a.institute_id = ?1 and a.is_active = 1", nativeQuery = true)
    List<InstituteAcademicYearEntity> findByInstituteIdWithName(String instituteId);

    boolean existsByYearIdAndInstituteId(String yearId, String instituteId);

    Optional<InstituteAcademicYearEntity> findByIdAndActive(String academicYearId, boolean b);
}
