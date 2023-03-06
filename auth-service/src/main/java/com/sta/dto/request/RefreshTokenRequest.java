package com.sta.dto.request;

import javax.validation.constraints.NotBlank;

import com.sta.utilities.AuthConstants;

import lombok.Data;

/**
 * Refresh token generate. Once the expire time of access token is exceed, then
 * make a call with refresh token which pass along with the login API. This
 * request will generate new access token.
 * 
 * @author r@ghu
 *
 */
@Data
public class RefreshTokenRequest {
	
	@NotBlank(message = AuthConstants.MANDATORY)
	private String refreshToken;
}
