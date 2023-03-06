package com.sta.settings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sta.settings.dto.response.RolesResponse;
import com.sta.settings.entities.RolesEntity;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, String>, JpaSpecificationExecutor<RolesEntity> {
	
	boolean existsByRole(String role);
	
	boolean existsByRoleIn(List<String> role);
	
	boolean existsByRoleIdAndIdNot(String role, String id);
	
	boolean existsByRoleIdAndInstitutesEntityId(String role, String instituteId);
	
	boolean existsByRoleIdAndInstitutesEntityIdAndIdNot(String role, String instituteId, String id);
	
	@Query("select new com.sta.settings.dto.response.RolesResponse(r.id, r.role.name, r.active) "
			+ "from #{#entityName} r where r.active = true")
	List<RolesResponse> findAllRoles();
	
	@Query("select new com.sta.settings.dto.response.RolesResponse(r.id, r.role.name, r.active) "
			+ "from #{#entityName} r where r.active = true and r.id in (:ids)")
	List<RolesResponse> findAllRolesByIds(List<String> ids);

}