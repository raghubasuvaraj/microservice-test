package com.sta.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sta.enums.ErrorCodes;
import com.sta.exception.AuthException;
import com.sta.utilities.AuthConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * 1. Get the identification of the login user.
 * <p>
 * 2. From the header extracting the token.
 * <p>
 * 3. To the header setting the token.
 * 
 * @author r@ghu
 *
 */
@Slf4j
@Service
public class SecurityUtil {

	/**
	 * Get logged in user's primary key
	 * 
	 * @return
	 */
	public String getCurrentUser() {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		return loggedInUser.getName();
	}

	/**
	 * Extracting the token from the request head
	 * 
	 * @return
	 */
	public String getToken() {
		try {
			String token = null;
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			if (attributes != null) {
				HttpServletRequest request = attributes.getRequest();
				token = request.getHeader(AuthConstants.AUTH_HEADER_KEY);
			}
			return token;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new AuthException(ErrorCodes.INTERNAL_SERVER_ERROR, "");
		}
	}

	/**
	 * Setting the extracted token to the header
	 * 
	 * @return
	 */
	public HttpHeaders getHttpHeaders() {
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.add(AuthConstants.AUTH_HEADER_KEY, getToken());
			return httpHeaders;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new AuthException(ErrorCodes.INTERNAL_SERVER_ERROR, "");
		}
	}
}
