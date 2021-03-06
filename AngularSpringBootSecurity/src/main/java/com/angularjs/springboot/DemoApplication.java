package com.angularjs.springboot;
 
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.angularjs.springboot.mongo.model.User;
import com.angularjs.springboot.service.user.UserService;
 
 @SpringBootApplication
 @RestController
 public class DemoApplication {
	 
	@Autowired
	private UserService service;
	
	public DemoApplication() {
		
	}

	// Match everything without a suffix (so not a static resource)
	/*@RequestMapping(value = "/{[path:[^\\.]*}")
	public String redirect() {
		// Forward to home page so that route is preserved.
		return "forward:/";
	}
*/
	@RequestMapping("/user")
	public Principal user(Principal user) {
		System.out.println("user : "  + user);
		
		for (User user1 : service.readAll()) {
			System.out.println("user : " + user1);
		}
		
		return user;
	}
 	
	@RequestMapping("/resource")
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		return model;
	}
	
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
	 
 }