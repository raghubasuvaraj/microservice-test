package com.sta.settings.repository;

import com.sta.settings.entities.InstituteTermEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstituteTermRepository extends JpaRepository<InstituteTermEntity, String>, JpaSpecificationExecutor<InstituteTermEntity> {

    List<InstituteTermEntity> findByInstituteIdAndActive(String instituteId, boolean b);

    List<InstituteTermEntity> findByInstituteIdAndTermTypeIdAndActive(String instituteId, String termTypeId, boolean b);
}
