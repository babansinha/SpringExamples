package com.angularjs.springboot.config;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.angularjs.springboot.config.cors.CORSFilter;
import com.angularjs.springboot.config.csrf.CsrfTokenResponseCookieBindingFilter;
import com.angularjs.springboot.service.auth.UserDetailsService;


@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

	@Resource
	private AuthenticationEntryPoint authenticationEntryPoint;
	
	@Resource
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Resource
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Resource
	private CORSFilter corsFilter;
	
	@Resource
	private LogoutSuccessHandler logoutSuccessHandler;
	
	@Resource
    private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		/*builder.inMemoryAuthentication().withUser("user").password("user").roles("USER").and().withUser("admin")
			.password("admin").roles("ADMIN");*/
		builder.userDetailsService(userDetailsService);
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().and().authorizeRequests().antMatchers("/index.html", "/home.html", "/login.html", "/")
				.permitAll().anyRequest().authenticated().and().csrf().csrfTokenRepository(csrfTokenRepository()).and()
				.addFilterAfter(new CsrfTokenResponseCookieBindingFilter(), CsrfFilter.class);
		

		
		// Handlers and entry points
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		http.formLogin().successHandler(authenticationSuccessHandler);
		http.formLogin().failureHandler(authenticationFailureHandler);

		// Logout
		http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);

		// CORS
		http.addFilterBefore(corsFilter, ChannelProcessingFilter.class);
		
		// CSRF
		
		http.addFilterAfter(new CsrfTokenResponseCookieBindingFilter(), CsrfFilter.class); // CSRF tokens handling

	}
	
	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}
}
