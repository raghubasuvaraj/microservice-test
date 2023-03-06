package com.sta.settings.repository;

import com.sta.settings.entities.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity, String>, JpaSpecificationExecutor<LanguageEntity> {

}
