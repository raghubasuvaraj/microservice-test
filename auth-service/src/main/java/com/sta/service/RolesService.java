package com.sta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sta.dto.response.RolesResponse;

@Service
public interface RolesService {

	List<RolesResponse> createRoles(List<String> roles);

	List<RolesResponse> getAllRoles();

}
