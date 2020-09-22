package com.leoncarraro.springsecurityapi.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.leoncarraro.springsecurityapi.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {

	ADMIN(Sets.newHashSet()),
	STUDENT(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE, COURSE_READ, COURSE_WRITE));
	
	private final Set<ApplicationUserPermission> permission;

	private ApplicationUserRole(Set<ApplicationUserPermission> permission) {
		this.permission = permission;
	}

	public Set<ApplicationUserPermission> getPermission() {
		return permission;
	}
	
}
