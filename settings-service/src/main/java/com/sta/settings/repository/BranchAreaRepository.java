package com.sta.settings.repository;

import com.sta.settings.entities.BranchAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchAreaRepository extends JpaRepository<BranchAreaEntity, String> {

    List<BranchAreaEntity> findByBranchIdIs(String branchId);
}
