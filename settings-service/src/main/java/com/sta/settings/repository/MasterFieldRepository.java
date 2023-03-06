package com.sta.settings.repository;

import com.sta.settings.entities.MasterFieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MasterFieldRepository  extends JpaRepository<MasterFieldEntity, String> {
    boolean existsByInstituteId(String instituteId);
}
