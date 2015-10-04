package com.angularjs.springboot.auth.handlers;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * An authentication entry point implementation adapted to a REST approach.
 */
public class RESTAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
	                     AuthenticationException authException) throws IOException, ServletException {
		System.out.println("RESTAuthenticationEntryPoint. . .");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}
}
