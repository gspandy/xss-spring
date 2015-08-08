package com.ryanperrizo.spring.sample.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryanperrizo.spring.sample.entities.Applicant;
import com.ryanperrizo.spring.sample.repositories.ApplicantRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private ApplicantRepository applicantRepository;
	
	@Autowired
	public AdminServiceImpl(ApplicantRepository applicantRepository){
		this.applicantRepository = applicantRepository;
	}
	
	@Override
	public List<Applicant> search(String searchText){	
		return applicantRepository.searchApplicants(searchText);
	}
	
}
