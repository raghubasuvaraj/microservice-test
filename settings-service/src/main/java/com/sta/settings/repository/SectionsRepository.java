package com.sta.settings.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sta.settings.entities.SectionEntity;

@Repository
public interface SectionsRepository extends JpaRepository<SectionEntity, String> {
	
	boolean existsByName(String name);
	
	boolean existsByIdIn(List<String> ids);
	
	boolean existsByNameAndActive(String name, boolean active);
	
	boolean existsByNameAndBranchInfoEntityIdAndClassGroupEntityIdAndActive(String name, String branchId, String classId, boolean active);
	
	List<SectionEntity> findAllByClassGroupEntityIdAndActive(String classId, boolean active);
	
	@Query("select se.id from #{#entityName} se where se.branchInfoEntity.id=:branchId and se.classGroupEntity.id=:classId and se.active=:active")
	Set<String> findActiveIdByBranchAndClass(String branchId, String classId, boolean active);
	
	@Transactional
	@Modifying
	@Query("update #{#entityName} se set se.active =:active, se.lastModifiedAt=:currentTime where se.id in(:ids)")
	int setActiveStatus(Set<String> ids, boolean active, Long currentTime);
}
