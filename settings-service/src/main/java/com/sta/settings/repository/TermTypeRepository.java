package com.sta.settings.repository;

import com.sta.settings.entities.MasterTermTypeEntity;
import com.sta.settings.entities.ModuleEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TermTypeRepository extends JpaRepository<MasterTermTypeEntity, String> {

    List<MasterTermTypeEntity> findAll(Specification<MasterTermTypeEntity> spec);
}
