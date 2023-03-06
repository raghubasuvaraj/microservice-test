package com.sta.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sta.dto.request.AuthRequest;
import com.sta.dto.request.RefreshTokenRequest;
import com.sta.dto.response.AuthResponse;
import com.sta.service.AuthService;

/**
 * API end points only for authentication and authorisation.
 * @author r@ghu
 *
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
		AuthResponse response = authService.login(request);
		return new ResponseEntity<>(response,
				(!ObjectUtils.isEmpty(response)) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/refresh-token")
	public ResponseEntity<AuthResponse> refreshTokenGenerate(@Valid @RequestBody RefreshTokenRequest request) {
		AuthResponse response = authService.refreshTokenGenerate(request);
		return new ResponseEntity<>(response,
				(!ObjectUtils.isEmpty(response)) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest request) {
		String response = authService.logout(request);
		return new ResponseEntity<>(response,
				(!ObjectUtils.isEmpty(response)) ? HttpStatus.NO_CONTENT : HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
