package com.leoncarraro.springsecurityapi.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.base.Strings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtTokenVerifierFilter extends OncePerRequestFilter {

	private JwtSecretKey jwtSecretKey;
	private JwtConfig jwtConfig;
	
	public JwtTokenVerifierFilter(
		JwtSecretKey jwtSecretKey, 
		JwtConfig jwtConfig) {
		
		this.jwtSecretKey = jwtSecretKey;
		this.jwtConfig = jwtConfig;
	}

	@Override
	protected void doFilterInternal(
		HttpServletRequest request, 
		HttpServletResponse response, 
		FilterChain filterChain) throws ServletException, IOException {
		
		String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());
		if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
			filterChain.doFilter(request, response);
			return;
		}
		
		try {
			String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
			
			Jws<Claims> claimsJws = Jwts.parserBuilder()
				.setSigningKey(jwtSecretKey.getSecretKey())
				.build()
				.parseClaimsJws(token);
			Claims body = claimsJws.getBody();
			
			@SuppressWarnings("unchecked")
			var authorities = (List<Map<String, String>>) body.get("authorities");
			
			Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream().map(a -> new SimpleGrantedAuthority(a.get("authority"))).collect(Collectors.toSet());
			
			String username = body.getSubject();
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (JwtException e) {
			throw new IllegalStateException("JWT token cannot be trusted!");
		}
	
		filterChain.doFilter(request, response);
	}
}
