package com.sta.settings.repository;

import com.sta.settings.entities.InstituteSystemInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutesSystemInfoRepository extends JpaRepository<InstituteSystemInfoEntity, String> {
    boolean existsByInstituteId(String instituteId);
    @Query(value = "select * from mse_institute_system_info where institute_id = ?1 and is_active = 1", nativeQuery = true)
    InstituteSystemInfoEntity findByInstituteId(String id);

    @Query(value = "select * from mse_institute_system_info where is_active = 1", nativeQuery = true)
    List<InstituteSystemInfoEntity> findAll();

}
