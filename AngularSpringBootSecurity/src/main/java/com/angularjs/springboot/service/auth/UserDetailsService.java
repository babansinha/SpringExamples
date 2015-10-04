package com.angularjs.springboot.service.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername . . . " + username);
		if (username != null) {
			// TODO Auto-generated method stub
			return new UserDetails() {

				/**
				 * 
				 */
				private static final long serialVersionUID = -7391266797545932726L;

				@Override
				public boolean isEnabled() {
					// TODO Auto-generated method stub
					return true;
				}

				@Override
				public boolean isCredentialsNonExpired() {
					// TODO Auto-generated method stub
					return true;
				}

				@Override
				public boolean isAccountNonLocked() {
					// TODO Auto-generated method stub
					return true;
				}

				@Override
				public boolean isAccountNonExpired() {
					// TODO Auto-generated method stub
					return true;
				}

				@Override
				public String getUsername() {
					return username;
				}

				@Override
				public String getPassword() {
					return "user";
				}

				@Override
				public Collection<? extends GrantedAuthority> getAuthorities() {
					return null;
				}
			};

		} else {
			throw new UsernameNotFoundException("could not find the user '" + username + "'");
		}
	}

}
