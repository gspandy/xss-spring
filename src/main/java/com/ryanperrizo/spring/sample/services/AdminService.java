package com.ryanperrizo.spring.sample.services;

import java.util.List;

import com.ryanperrizo.spring.sample.entities.Applicant;

public interface AdminService {

	List<Applicant> search(String searchText);

	
}
