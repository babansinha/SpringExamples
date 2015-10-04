package com.angularjs.springboot.auth.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * An authentication logout success handler implementation adapted to a REST approach.
 */
public class RESTLogoutSuccessHandler implements LogoutSuccessHandler {
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
		throws IOException, ServletException {
		System.out.println("RESTLogoutSuccessHandler. . .");
		// Do... absolutely... nothing! If you've reached this, the user session has been removed already!
	}
}
