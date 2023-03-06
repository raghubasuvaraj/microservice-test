package com.sta.settings.repository;

import com.sta.settings.entities.MasterBranchTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterBranchTypeRepository extends JpaRepository<MasterBranchTypeEntity, String>, JpaSpecificationExecutor<MasterBranchTypeEntity> {

}
