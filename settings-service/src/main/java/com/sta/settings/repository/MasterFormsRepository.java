package com.sta.settings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sta.settings.entities.MasterFormsEntity;

/**
 * @author r@ghu
 *
 */
@Repository
public interface MasterFormsRepository extends JpaRepository<MasterFormsEntity, String>, JpaSpecificationExecutor<MasterFormsEntity> {

//	boolean existsByNameAndShortCodeAndCurriculumsEntityIdAndActive(String name, String shortCode, String curriculumId,
//			boolean active);
//
//	boolean existsByNameAndShortCodeAndCurriculumsEntityIdAndIdNot(String name, String shortCode, String curriculumId,
//			String id);
//
//	List<MasterFormsEntity> findAllByCurriculumsEntityIdAndActive(String curriculumId, boolean active);
//
//	@Query("select mf.name from #{#entityName} mf where mf.active=true and mf.id=:id")
//	String findNameByIdAndActive(String id);
//
//	@Modifying
//	@Transactional
//	@Query("update #{#entityName} set status=:status, lastModifiedAt=:lastModifiedAt where active=true and id=:id")
//	int setApprovedStatusById(String id, Long lastModifiedAt, String status);
//
//	@Modifying
//	@Transactional
//	@Query("update #{#entityName} set active=false, lastModifiedAt=:lastModifiedAt where active=true and id=:id")
//	int setActiveStatusById(String id, Long lastModifiedAt);
//
//	@Modifying
//	@Transactional
//	@Query("update #{#entityName} set active=false, lastModifiedAt=:lastModifiedAt "
//			+ "where active=true and curriculumsEntity.id=:curriculumId")
//	int setActiveStatusByCurriculumId(String curriculumId, Long lastModifiedAt);
}
