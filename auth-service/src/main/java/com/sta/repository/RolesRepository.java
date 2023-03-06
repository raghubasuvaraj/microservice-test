package com.sta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sta.dto.response.RolesResponse;
import com.sta.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, String> {
	
	boolean existsById(String id);
	
	boolean existsByRole(String role);
	
	@Query("SELECT new com.sta.dto.response.RolesResponse(r.id, r.role) "
			+ "FROM #{#entityName} r WHERE r.id=:id")
	RolesResponse findByRoleId(@Param("id") String id);
	
	@Query("SELECT new com.sta.dto.response.RolesResponse(r.id, r.role) FROM #{#entityName} r")
	List<RolesResponse> findAllRoles();
}
