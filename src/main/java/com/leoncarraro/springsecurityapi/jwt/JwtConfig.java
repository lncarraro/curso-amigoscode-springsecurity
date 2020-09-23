package com.leoncarraro.springsecurityapi.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.google.common.net.HttpHeaders;

@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {

	private String tokenPrefix;
	private String secretKey;
	private Integer tokenExpirationAfterDays;
	
	public JwtConfig() {
	}
	
	public JwtConfig(String tokenPrefix, String secretKey, Integer tokenExpirationAfterDays) {
		this.tokenPrefix = tokenPrefix;
		this.secretKey = secretKey;
		this.tokenExpirationAfterDays = tokenExpirationAfterDays;
	}

	public String getTokenPrefix() {
		return tokenPrefix;
	}

	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public Integer getTokenExpirationAfterDays() {
		return tokenExpirationAfterDays;
	}

	public void setTokenExpirationAfterDays(Integer tokenExpirationAfterDays) {
		this.tokenExpirationAfterDays = tokenExpirationAfterDays;
	}
	
	public String getAuthorizationHeader() {
		return HttpHeaders.AUTHORIZATION;
	}
	
}
