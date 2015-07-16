package com.ryanperrizo.spring.sample.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Component;

public class SmtpMailSender implements MailSender {

	private static final Log log = LogFactory.getLog(MockMailSender.class);
	
	private JavaMailSender javaMailSender;
	
	public void setJavaMailSender(JavaMailSender javaMailSender){
		this.javaMailSender = javaMailSender;
	}
	
	/* (non-Javadoc)
	 * @see com.ryanperrizo.spring.sample.mail.MailSender#send(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void send(String to, String subject, String body) throws MessagingException{
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		helper = new MimeMessageHelper(message, true); //true indicates multipart
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true);
		
		javaMailSender.send(message);
	}
	
}
