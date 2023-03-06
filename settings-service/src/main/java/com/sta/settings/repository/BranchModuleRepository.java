package com.sta.settings.repository;

import com.sta.settings.entities.BranchModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchModuleRepository extends JpaRepository<BranchModuleEntity, String> {

    @Query(value = "Select b from BranchModuleEntity b where b.branchId = ?1 and b.active = 1")
    List<BranchModuleEntity> findByBranchId(String id);

    @Query(value = "Select b from BranchModuleEntity b where b.active = 1")
    List<BranchModuleEntity> findAllActive();

    @Query(value = "Select module_id from mse_branch_modules where branch_id = ?1 and module_id in (?2) and is_active = 1", nativeQuery = true)
    List<String> findByBranchIdOrModuleIdIn(String branchId, List<String> moduleIds);
}
