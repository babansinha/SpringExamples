package com.angularjs.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.angularjs.springboot.mongo.model.User;


public interface UserRepository extends MongoRepository<User, String> {

	User findByUsername(String username);

}
