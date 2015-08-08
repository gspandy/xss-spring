package com.ryanperrizo.spring.sample.validators;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.ryanperrizo.spring.sample.dto.SearchForm;
import com.ryanperrizo.spring.sample.repositories.ApplicantRepository;


@Component
public class SearchFormValidator extends LocalValidatorFactoryBean {
	
//	private ApplicantRepository applicantRepository;
//	
//	@Resource
//	public void setUserRepository(ApplicantRepository applicantRepository){
//		this.applicantRepository = applicantRepository;
//	}
	
	@Override
	public boolean supports(Class<?> clazz){
		return clazz.isAssignableFrom(SearchForm.class);
	}
	
	@Override
	public void validate(Object obj, Errors errors, final Object... validationMints){
		
		super.validate(obj, errors, validationMints);
		
//		if (!errors.hasErrors()){
//			SearchForm searchForm = (SearchForm) obj;
//			Applicant applicant = applicantRepository.findByEmail(signupForm.getEmail());
//			if (applicant != null)
//				errors.rejectValue("email", "emailNotUnique");
//		}
	}
	
	
}

