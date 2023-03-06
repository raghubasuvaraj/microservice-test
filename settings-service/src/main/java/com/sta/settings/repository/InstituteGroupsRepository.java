package com.sta.settings.repository;

import com.sta.settings.entities.MasterInstituteGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 * @author r@ghu
 *
 */
@Repository
public interface InstituteGroupsRepository extends JpaRepository<MasterInstituteGroups, String>, JpaSpecificationExecutor<MasterInstituteGroups> {

	boolean existsByInstituteGroupNameAndAffliationNoAndActive(String instituteGroupName, String affliationNo,
			boolean active);

	boolean existsByInstituteGroupNameAndAffliationNoAndActiveAndIdNot(String instituteGroupName, String affliationNo,
			boolean active, String id);

	@Query("update #{#entityName} ig set ig.active=false, ig.lastModifiedAt=:currentTime where ig.active=:active and ig.id=:id")
	int setActiveStatus(Long currentTime, String id, boolean active);
}
