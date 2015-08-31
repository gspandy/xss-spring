package com.ryanperrizo.spring.sample.validators;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.ryanperrizo.spring.sample.dto.ForgotPasswordForm;
import com.ryanperrizo.spring.sample.entities.User;
import com.ryanperrizo.spring.sample.repositories.UserRepository;

@Component
public class ForgotPasswordFormValidator extends LocalValidatorFactoryBean {
	
	private UserRepository userRepository;
	
	@Resource
	public void setUserRepository(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	@Override
	public boolean supports(Class<?> clazz){
		return clazz.isAssignableFrom(ForgotPasswordForm.class);
	}
	
	@Override
	public void validate(Object obj, Errors errors, final Object... validationMints){
		
		super.validate(obj, errors, validationMints);
		
		if (!errors.hasErrors()){
			ForgotPasswordForm forgotPasswordForm = (ForgotPasswordForm) obj;
			User user = userRepository.findByEmail(forgotPasswordForm.getEmail());
			if (user == null)
				errors.rejectValue("email", "notFound");
		}
	}
	
	
}
