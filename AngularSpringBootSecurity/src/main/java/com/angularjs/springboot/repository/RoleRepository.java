package com.angularjs.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.angularjs.springboot.mongo.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

}
