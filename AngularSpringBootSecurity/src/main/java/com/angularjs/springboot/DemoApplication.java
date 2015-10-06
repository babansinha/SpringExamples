package com.angularjs.springboot;
 
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angularjs.springboot.mongo.model.Role;
import com.angularjs.springboot.mongo.model.User;
import com.angularjs.springboot.service.user.UserService;
 
 @SpringBootApplication
 @RestController
 public class DemoApplication {
	 
	@Autowired
	private UserService service;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	 
	public DemoApplication() {
		
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
		
		User john = new User();
		john.setId(UUID.randomUUID().toString());
		john.setFirstName("John");
		john.setLastName("Smith");
		john.setPassword("21232f297a57a5a743894a0e4a801fc3");
		john.setRole(adminRole);
		john.setUsername("john");
		
		User jane = new User();
		jane.setId(UUID.randomUUID().toString());
		jane.setFirstName("Jane");
		jane.setLastName("Adams");
		jane.setPassword("ee11cbb19052e40b07aac0ca060c23ee");
		jane.setRole(userRole);
		jane.setUsername("jane");
		
		// Insert to db
		mongoTemplate.insert(john, "user");
		mongoTemplate.insert(jane, "user");
		mongoTemplate.insert(adminRole, "role");
		mongoTemplate.insert(userRole, "role");
	}
	
	@RequestMapping("/user")
	public Principal user(Principal user) {
		System.out.println("user : "  + user);
		
		//init();
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