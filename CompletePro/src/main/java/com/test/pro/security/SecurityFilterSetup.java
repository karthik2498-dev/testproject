package com.test.pro.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityFilterSetup {

	
	
	@Bean
	public SecurityFilterChain securityfilter(HttpSecurity http) throws Exception {
		
		
		http.csrf(csrf->csrf.disable()).authorizeHttpRequests(auth->auth.requestMatchers("/public/**")
				.permitAll()
				.requestMatchers("/token/**")
				.permitAll()
				.anyRequest()
				.authenticated())
		.oauth2ResourceServer(oauth->oauth.jwt(jwt->jwt.jwkSetUri("https://dev-65152907.okta.com/oauth2/default/v1/keys")));
		
		return http.build();
		
	}
	
}
