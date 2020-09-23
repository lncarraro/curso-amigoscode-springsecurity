package com.leoncarraro.springsecurityapi.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.leoncarraro.springsecurityapi.security.ApplicationUserRole;

@Repository(value = "fake")
public class FakeApplicationUserDaoService implements ApplicationUserDAO {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		return getApplicationUsers()
			.stream()
			.filter(user -> username.equals(user.getUsername()))
			.findFirst();
	}
	
	private List<ApplicationUser> getApplicationUsers() {
		List<ApplicationUser> applicationUsers = Lists.newArrayList(
				new ApplicationUser(
					"annasmith",
					passwordEncoder.encode("student"),
					ApplicationUserRole.STUDENT.getGrantedAuthorities(),
					true, true, true, true),
				
				new ApplicationUser(
					"linda",
					passwordEncoder.encode("admin"),
					ApplicationUserRole.ADMIN.getGrantedAuthorities(),
					true, true, true, true),
				
				new ApplicationUser(
					"tom",
					passwordEncoder.encode("admintrainee"),
					ApplicationUserRole.ADMINTRAINEE.getGrantedAuthorities(),
					true, true, true, true)
		);
		
		return applicationUsers;
	}

}
