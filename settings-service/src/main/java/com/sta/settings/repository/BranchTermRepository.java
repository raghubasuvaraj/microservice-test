package com.sta.settings.repository;

import com.sta.settings.entities.BranchTermEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchTermRepository extends JpaRepository<BranchTermEntity, String>, JpaSpecificationExecutor<BranchTermEntity> {
    List<BranchTermEntity> findByBranchId(String branchId);
}
