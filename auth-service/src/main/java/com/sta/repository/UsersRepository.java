package com.sta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sta.dto.response.UsersResponse;
import com.sta.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

	@Query("select new com.sta.dto.response.UsersResponse(u.id, u.name, u.mobile, u.email) from #{#entityName} u")
	List<UsersResponse> findAllUsersToSuperAdmin();
}
