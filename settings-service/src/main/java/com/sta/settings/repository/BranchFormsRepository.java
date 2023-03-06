package com.sta.settings.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.sta.settings.enums.CommonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sta.settings.dto.response.MasterFormsFromBranchResponse;
import com.sta.settings.entities.BranchFormsEntity;

/**
 * Assign form to branch, interaction with DB
 * 
 * @author r@ghu
 *
 */
@Repository
public interface BranchFormsRepository extends JpaRepository<BranchFormsEntity, String> {

//	boolean existsByBranchInfoEntityIdAndMasterFormsEntityIdAndActive(String branchId, String formId, boolean active);

	boolean existsByBranchInfoEntityIdAndMasterFormsEntityIdAndActiveAndIdNot(String branchId, String formId, boolean active,
			String id);

//	BranchFormsEntity findByBranchInfoEntityIdAndMasterFormsEntityIdAndActive(String branchId, String formId,
//			boolean active);

//	BranchFormsEntity findByBranchInfoEntityIdAndActive(String branchId, boolean active);

	List<BranchFormsEntity> findAllByBranchInfoEntityIdAndActive(String branchId, boolean active);

//	@Query("select distinct new com.sta.settings.dto.response.MasterFormsFromBranchResponse(afb.masterFormsEntity.id, afb.formUrl) "
//			+ "from #{#entityName} afb where afb.active=:active")
//	List<MasterFormsFromBranchResponse> findMasterFormIdByActive(boolean active);

//	@Query("select distinct afb.branchInfoEntity.id from #{#entityName} afb where afb.active=:active and afb.masterFormsEntity.id=:masterId")
//	List<String> findAllBranchByMasterFormIdAndActive(String masterId, boolean active);

//	List<BranchFormsEntity> findAllByMasterFormsEntityIdInAndActive(List<String> formId, boolean active);

	@Transactional
	@Modifying
	@Query("update #{#entityName} afb set afb.active=:active, afb.lastModifiedAt=:currentTime, afb.status=:status, afb.deletedAt=:currentTime "
			+ "where afb.branchInfoEntity.id=:branchId and afb.active=true")
	int setActiveStatus(boolean active, LocalDateTime currentTime, String branchId, CommonStatus status);

	List<BranchFormsEntity> findByActiveAndMasterFormsEntityIdIn(boolean b, List<String> masterFormIds);
}
