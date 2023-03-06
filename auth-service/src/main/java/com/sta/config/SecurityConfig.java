package com.sta.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @EnableGlobalMethodSecurity will help to give access-privilege to a specific
 *                             APIs by checking the current user's roles.
 * @author r@ghu
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/api/v1/auth/**").permitAll().anyRequest()
				.authenticated().and().oauth2ResourceServer().jwt()
				.jwtAuthenticationConverter(jwtAuthenticationConverter());

		return http.build();
	}

	/**
	 * Convert realm_access.roles claims to granted authorities, for use in access
	 * decisions.
	 * 
	 * @return
	 */
	private JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
		return jwtAuthenticationConverter;
	}

	class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

		@Override
		@SuppressWarnings("unchecked")
		public Collection<GrantedAuthority> convert(final Jwt jwt) {
			final Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
			return ((List<String>) realmAccess.get("roles")).stream().map(roleName -> "ROLE_" + roleName)
					.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		}

	}
}
