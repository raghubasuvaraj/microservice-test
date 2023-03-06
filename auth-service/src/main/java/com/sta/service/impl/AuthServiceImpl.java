package com.sta.service.impl;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.sta.dto.request.AuthRequest;
import com.sta.dto.request.RefreshTokenRequest;
import com.sta.dto.response.AuthResponse;
import com.sta.enums.ErrorCodes;
import com.sta.exception.AuthException;
import com.sta.exception.ErrorModel;
import com.sta.service.AuthService;
import com.sta.utilities.AuthConstants;
import com.sta.utilities.AuthMessages;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("authService")
public class AuthServiceImpl implements AuthService {

	@Value("${keycloak.logout}")
	private String keycloakLogout;

	@Value("${keycloak.admin.realm}")
	private String realm;

	@Value("${keycloak.client-id}")
	private String clientId;

	@Value("${keycloak.server-url}")
	private String serverUrl;

	@Value("${keycloak.client-secret}")
	private String clientSecret;

	@Value("${keycloak.token-uri}")
	private String keycloakTokenUri;

	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public AuthResponse login(AuthRequest request) {
		try {
			AuthResponse response = null;
			Keycloak keycloak = KeycloakBuilder.builder().serverUrl(serverUrl).realm(realm)
					.username(request.getUsername()).password(request.getPassword()).clientId(clientId)
					.clientSecret(clientSecret).build();
			AccessTokenResponse accessTokenResponse = keycloak.tokenManager().getAccessToken();
			if (accessTokenResponse != null) {
				log.info("Access token generated.");
				response = new AuthResponse(accessTokenResponse.getToken(), accessTokenResponse.getExpiresIn(),
						accessTokenResponse.getRefreshExpiresIn(), accessTokenResponse.getRefreshToken(),
						accessTokenResponse.getTokenType(), accessTokenResponse.getIdToken(),
						accessTokenResponse.getSessionState(), accessTokenResponse.getScope());
			}
			return response;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			Gson gson = new Gson();
			if (e instanceof HttpClientErrorException) {
				HttpClientErrorException httpException = (HttpClientErrorException) e;
				ErrorModel errorModel = gson.fromJson(httpException.getResponseBodyAsString(), ErrorModel.class);
				throw new AuthException(ErrorCodes.BAD_REQUEST, errorModel.getError_description());
			} else
				throw new AuthException(ErrorCodes.INTERNAL_SERVER_ERROR, AuthMessages.LOGIN_FAILED);
		}
	}

	@Override
	public AuthResponse refreshTokenGenerate(RefreshTokenRequest request) {
		try {
			MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
			multiValueMap.add(AuthConstants.GRANT_TYPE, AuthConstants.REFRESH_TOKEN);
			multiValueMap.add(AuthConstants.CLIENT_ID, clientId);
			multiValueMap.add(AuthConstants.CLIENT_SECRET, clientSecret);
			multiValueMap.add(AuthConstants.REFRESH_TOKEN, request.getRefreshToken());

			HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(multiValueMap, null);
			return restTemplate.postForObject(keycloakTokenUri, httpEntity, AuthResponse.class);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			Gson gson = new Gson();
			if (e instanceof HttpClientErrorException) {
				HttpClientErrorException httpException = (HttpClientErrorException) e;
				ErrorModel errorModel = gson.fromJson(httpException.getResponseBodyAsString(), ErrorModel.class);
				throw new AuthException(ErrorCodes.BAD_REQUEST, errorModel.getError_description());
			} else
				throw new AuthException(ErrorCodes.INTERNAL_SERVER_ERROR, AuthMessages.REFRESH_TOKEN_FAILED);
		}
	}

	@Override
	public String logout(RefreshTokenRequest request) {
		try {
			MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
			multiValueMap.add(AuthConstants.CLIENT_ID, clientId);
			multiValueMap.add(AuthConstants.CLIENT_SECRET, clientSecret);
			multiValueMap.add(AuthConstants.REFRESH_TOKEN, request.getRefreshToken());

			HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(multiValueMap, null);
			ResponseEntity<String> response = restTemplate.exchange(keycloakLogout, HttpMethod.POST, httpEntity,
					String.class);
			log.info("STATUS CODE " + response.getStatusCodeValue());
			return (response.getStatusCodeValue() == 204) ? "Successfully logout" : "Logout failed";
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new AuthException(ErrorCodes.INTERNAL_SERVER_ERROR, AuthMessages.LOGOUT_FAILED);
		}
	}

}
