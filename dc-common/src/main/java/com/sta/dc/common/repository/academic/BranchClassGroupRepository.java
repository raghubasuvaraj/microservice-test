package com.sta.dc.common.repository.academic;

import com.sta.dc.common.entity.academic.BranchClassGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BranchClassGroupRepository extends JpaRepository<BranchClassGroupEntity, String> {

    List<BranchClassGroupEntity> findAllByActive(boolean active);

    List<BranchClassGroupEntity> findByBranchId(String branchId);
}
