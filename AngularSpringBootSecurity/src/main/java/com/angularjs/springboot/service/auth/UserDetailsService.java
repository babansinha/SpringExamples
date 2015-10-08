package com.angularjs.springboot.service.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.angularjs.springboot.constants.AuthConstants;
import com.angularjs.springboot.mongo.model.Role;
import com.angularjs.springboot.mongo.model.User;
import com.angularjs.springboot.service.user.UserService;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		init();
		System.out.println("loadUserByUsername . . .");
		User user = userService.findByUsername(username);
		System.out.println("user :: " + user);

		if(user != null) {
			return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
					AuthConstants.ENABLED, AuthConstants.ENABLED, AuthConstants.ENABLED, AuthConstants.ENABLED, getAuthorities(user.getRole().getRole()));
		}
		
		return null;
	}

	public List<SimpleGrantedAuthority> getAuthorities(Integer role) {
		List<SimpleGrantedAuthority> authList = new ArrayList<>();
		if (role.intValue() == 1) {
			authList.add(new SimpleGrantedAuthority(AuthConstants.ROLE_ADMIN));

		} else if (role.intValue() == 2) {
			authList.add(new SimpleGrantedAuthority(AuthConstants.ROLE_USER));
		}

		return authList;
	}
	
	public void init() {
		// Drop existing collections
		mongoTemplate.dropCollection("role");
		mongoTemplate.dropCollection("user");

		// Create new records
		Role adminRole = new Role();
		adminRole.setId(UUID.randomUUID().toString());
		adminRole.setRole(1);
		
		Role userRole = new Role();
		userRole.setId(UUID.randomUUID().toString());
		userRole.setRole(2);
		
		User admin = new User();
		admin.setId(UUID.randomUUID().toString());
		admin.setFirstName("Admin");
		admin.setLastName("Sinha");
		admin.setPassword("admin");
		admin.setRole(adminRole);
		admin.setUsername("admin");
		
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setFirstName("User");
		user.setLastName("Sinha");
		user.setPassword("user");
		user.setRole(userRole);
		user.setUsername("user");
		
		// Insert to db
		/*mongoTemplate.insert(admin, "user");
		mongoTemplate.insert(user, "user");
		mongoTemplate.insert(adminRole, "role");
		mongoTemplate.insert(userRole, "role");*/
		
		userService.create(admin);
		//service.create(user);
	}

}
