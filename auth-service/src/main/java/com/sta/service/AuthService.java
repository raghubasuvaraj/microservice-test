package com.sta.service;

import org.springframework.stereotype.Service;

import com.sta.dto.request.AuthRequest;
import com.sta.dto.request.RefreshTokenRequest;
import com.sta.dto.response.AuthResponse;

/**
 * @author r@ghu
 *
 */
@Service
public interface AuthService {

	AuthResponse login(AuthRequest request);

	AuthResponse refreshTokenGenerate(RefreshTokenRequest request);

	String logout(RefreshTokenRequest request);

}
