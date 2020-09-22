package com.leoncarraro.springsecurityapi.security;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.leoncarraro.springsecurityapi.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {

	ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE, COURSE_READ, COURSE_WRITE)),
	ADMINTRAINEE(Sets.newHashSet(STUDENT_READ, COURSE_READ)),
	STUDENT(Sets.newHashSet());
	
	private final Set<ApplicationUserPermission> permissions;

	private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
			.map(p -> new SimpleGrantedAuthority(p.getPermission()))
			.collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		
		return permissions;
	}
	
}
