package com.ryanperrizo.spring.sample.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.ryanperrizo.spring.sample.entities.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
	
	@Query(value = "SELECT * FROM spring.applications WHERE LOWER(first_name) LIKE LOWER(CONCAT(:searchText)) OR LOWER(last_name) LIKE LOWER(CONCAT(:searchText))", nativeQuery=true) // query is broken
	List<Applicant> searchApplicants(@Param("searchText") String searchText);

	
}
