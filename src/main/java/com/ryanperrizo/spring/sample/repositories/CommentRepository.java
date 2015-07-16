package com.ryanperrizo.spring.sample.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ryanperrizo.spring.sample.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	@Query(value = "SELECT * FROM spring.comments ORDER BY id DESC", nativeQuery = true)
	List<Comment> findAll();
}
