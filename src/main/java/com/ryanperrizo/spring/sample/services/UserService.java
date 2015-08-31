package com.ryanperrizo.spring.sample.services;

import java.util.List;

import com.ryanperrizo.spring.sample.dto.ApplicantForm;
import com.ryanperrizo.spring.sample.dto.CommentForm;
import com.ryanperrizo.spring.sample.dto.ForgotPasswordForm;
import com.ryanperrizo.spring.sample.dto.SignupForm;
import com.ryanperrizo.spring.sample.entities.Comment;
import com.ryanperrizo.spring.sample.entities.User;

public interface UserService {
	
	public abstract void signup(SignupForm signupForm);

	public abstract void verify(String verificationCode);

	public abstract void reverify();

	public abstract User findOne(long userId);

	public abstract void addComment(CommentForm commentForm);

	public abstract List<Comment> viewComments();

	public abstract void apply(ApplicantForm applicantForm);

	public abstract void forgotPassword(ForgotPasswordForm forgotPasswordForm);

}
