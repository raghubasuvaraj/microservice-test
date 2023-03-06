package com.sta.settings.repository;

import com.sta.settings.entities.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<ModuleEntity, String> {

	/**
	 * In the entity ModuleEntity there is no field called branchId. So we cannot
	 * write this below method, it is causing error, so commenting it.
	 * 
	 * @param branchId
	 * @return
	 */
	//ModuleEntity findByBranchId(String branchId);
}
