package com.sta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sta.dto.response.UsersRoleResponse;
import com.sta.entity.UserRolesMapping;

@Repository
public interface UserRolesMappingRepository extends JpaRepository<UserRolesMapping, String> {

	boolean existsById(String id);

	boolean existsByUsersId(String usersId);

	@Query("SELECT new com.sta.dto.response.UsersRoleResponse(r.id, urm.roles.id) FROM #{#entityName} urm "
			+ "INNER JOIN com.sta.entity.Roles r ON r.id = urm.roles.id WHERE urm.users.id=:userId")
	List<UsersRoleResponse> findRolesByUserId(@Param("userId") String userId);
}
