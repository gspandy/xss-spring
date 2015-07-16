package com.ryanperrizo.spring.sample.mail;

import javax.mail.MessagingException;

public interface MailSender {

	public abstract void send(String to, String subject, String message) throws MessagingException;

}