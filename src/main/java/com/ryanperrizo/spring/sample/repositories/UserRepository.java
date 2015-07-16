package com.ryanperrizo.spring.sample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryanperrizo.spring.sample.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);//class and primary key type
	
}
