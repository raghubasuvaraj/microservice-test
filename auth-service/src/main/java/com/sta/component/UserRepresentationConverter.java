package com.sta.component;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sta.dto.request.KeycloakUserRequest;

/**
 * @author r@ghu
 *
 */
@Component
public class UserRepresentationConverter implements Converter<KeycloakUserRequest, UserRepresentation>{

	@Override
	public UserRepresentation convert(KeycloakUserRequest source) {
		UserRepresentation user = new UserRepresentation();
		user.setUsername(source.getEmail());
		user.setEmail(source.getEmail());
		user.setEnabled(true);
		return user;
	}

}
