package com.ryanperrizo.spring.sample.mail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class MockMailSender implements MailSender {

	private static final Log log = LogFactory.getLog(MockMailSender.class);
	
	/* (non-Javadoc)
	 * @see com.ryanperrizo.spring.sample.mail.MailSender#send(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void send(String to, String subject, String message){
		log.info("Sending mail to: " + to);
		log.info("Subject: " + subject);
		log.info("Body: " + message);
	}
	
}
