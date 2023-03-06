package com.sta.settings.repository;

import com.sta.settings.entities.BranchInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchInfoRepository extends JpaRepository<BranchInfoEntity, String> {
	
	boolean existsById(String id);
	
    boolean existsByInstituteId(String instituteId);
    boolean existsByInstituteIdAndBranchName(String instituteId, String branchName);
    List<BranchInfoEntity>  findByInstituteIdIs(String instituteId);

    BranchInfoEntity findByIdAndInstituteId(String branchId, String instituteId);
    Optional<BranchInfoEntity> findById(String branchId);

    Optional<BranchInfoEntity> findByIdAndActive(String branchId, boolean b);
    
    List<BranchInfoEntity>  findAllByIdIn(List<String> ids);
}
