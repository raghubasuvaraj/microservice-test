package com.sta.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Keycloak properties
 * 
 * @author r@ghu
 *
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "keycloak.admin")
public class KeycloakConfigProperties {

	private String keycloakUrl;
	private String adminUser;
	private String adminPassword;
	private String adminRealm;
	private String adminClientId;
	private String realm;
}
