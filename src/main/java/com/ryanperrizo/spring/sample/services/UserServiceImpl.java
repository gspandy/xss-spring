package com.ryanperrizo.spring.sample.services;


import java.util.Collections;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.ryanperrizo.spring.sample.dto.ApplicantForm;
import com.ryanperrizo.spring.sample.dto.CommentForm;
import com.ryanperrizo.spring.sample.dto.SignupForm;
import com.ryanperrizo.spring.sample.dto.UserDetailsImpl;
import com.ryanperrizo.spring.sample.entities.Applicant;
import com.ryanperrizo.spring.sample.entities.Comment;
import com.ryanperrizo.spring.sample.entities.User;
import com.ryanperrizo.spring.sample.entities.User.Role;
import com.ryanperrizo.spring.sample.mail.MailSender;
import com.ryanperrizo.spring.sample.repositories.ApplicantRepository;
import com.ryanperrizo.spring.sample.repositories.CommentRepository;
import com.ryanperrizo.spring.sample.repositories.UserRepository;
import com.ryanperrizo.spring.sample.util.MyUtil;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserServiceImpl implements UserService, UserDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private MailSender mailSender;
	private CommentRepository commentRepository;
	private ApplicantRepository applicantRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
			MailSender mailSender, CommentRepository commentRepository, ApplicantRepository applicantRepository){
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.mailSender = mailSender;
		this.commentRepository = commentRepository;
		this.applicantRepository = applicantRepository;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false) // do not save the user unless successful
	public void signup(SignupForm signupForm){
		User user = new User();
		user.setEmail(signupForm.getEmail());
		user.setName(signupForm.getName());
		user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		user.getRoles().add(Role.UNVERIFIED);
		user.setVerificationCode(RandomStringUtils.randomAlphanumeric(16));
		userRepository.save(user);
		
		//this is makes it so that if an error occurs after the send function, an email won't be sent when a user
		//hasn't been created in the database
		TransactionSynchronizationManager.registerSynchronization(
			    new TransactionSynchronizationAdapter() {
			        @Override
			        public void afterCommit() {
			    		try {
			    			String verifyLink = MyUtil.hostUrl() + "/users/" + user.getVerificationCode() + "/verify";
			    			mailSender.send(user.getEmail(), MyUtil.getMessage("verifySubject"), MyUtil.getMessage("verifyEmail", verifyLink));
			    			logger.info("Verification mail to " + user.getEmail() + " queued.");
						} catch (MessagingException e) {
							logger.error(ExceptionUtils.getStackTrace(e));
						}
			        }
		    });
		
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username); //find user
		if (user == null)
			throw new UsernameNotFoundException(username);
		return new UserDetailsImpl(user);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void verify(String verificationCode) {
		
		long loggedInUserId = MyUtil.getSessionUser().getId(); // add function to get user's id
		User user = userRepository.findOne(loggedInUserId); // find the user in the database
		
		MyUtil.validate(user.getRoles().contains(Role.UNVERIFIED), "alreadyVerified");
		MyUtil.validate(user.getVerificationCode().equals(verificationCode), "incorrect", "verification code");
		
		user.getRoles().remove(Role.UNVERIFIED);
		user.setVerificationCode(null);
		userRepository.save(user);

	}

	@Override
	public void reverify() {
		
		long loggedInUserId = MyUtil.getSessionUser().getId(); // add function to get user's id
		User user = userRepository.findOne(loggedInUserId); // find the user in the database
		
		try {
			String verifyLink = MyUtil.hostUrl() + "/users/" + user.getVerificationCode() + "/verify";
			mailSender.send(user.getEmail(), MyUtil.getMessage("verifySubject"), MyUtil.getMessage("verifyEmail", verifyLink));
			logger.info("Verification mail to " + user.getEmail() + " queued.");
		} catch (MessagingException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	@Override
	public User findOne(long userId) {
		User loggedIn = MyUtil.getSessionUser();
		User user = userRepository.findOne(userId);
		
		if (loggedIn == null ||
				loggedIn.getId() != user.getId() && !loggedIn.isAdmin())
			//hide the email id
			user.setEmail("Confidential");
		return user;
	}

	@Override
	public void addComment(CommentForm commentForm) {
		
		User loggedIn = MyUtil.getSessionUser();
		Comment comment = new Comment();
		comment.setName(loggedIn.getName());
		comment.setComment(commentForm.getComment());
		logger.info("COMMENT:"+comment.toString());
		commentRepository.save(comment);
		
	}

	@Override
	public List<Comment> viewComments() {
		// TODO Auto-generated method stub
		return commentRepository.findAll();
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void apply(ApplicantForm applicantForm) {
		// TODO Auto-generated method stub
		User loggedIn = MyUtil.getSessionUser();
		Applicant applicant = new Applicant();
		applicant.setAddress(applicantForm.getAddress());
		applicant.setBirthDate(applicantForm.getBirthDate());
		applicant.setFirstName(applicantForm.getFirstName());
		applicant.setLastName(applicantForm.getLastName());
		applicant.setCity(applicantForm.getCity());
		applicant.setZip(applicantForm.getZip());
		applicant.setSsn(applicantForm.getSsn());
		applicant.setGender(applicantForm.getGender());
		applicant.setEmail(loggedIn.getEmail());
		applicant.setDarkestSecret(applicantForm.getDarkestSecret());
		applicant.setState(applicantForm.getState());
		logger.info(applicant.toString());
		applicantRepository.save(applicant);
		
	}
	
	
	
}
