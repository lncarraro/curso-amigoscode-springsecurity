package com.leoncarraro.springsecurityapi.auth;

import java.util.Optional;

public interface ApplicationUserDAO {

	Optional<ApplicationUser> selectApplicationUserByUsername(String username);
	
}
