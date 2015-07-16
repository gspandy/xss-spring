package com.ryanperrizo.spring.sample.util;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ryanperrizo.spring.sample.dto.UserDetailsImpl;
import com.ryanperrizo.spring.sample.entities.User;
import com.ryanperrizo.spring.sample.services.UserServiceImpl;

@Component
public class MyUtil {
	
	@Autowired
	public MyUtil(MessageSource messageSource){
		MyUtil.messageSource = messageSource;
	}
	public static void flash(RedirectAttributes redirectAttributes, String kind, String messageKey){
		
		redirectAttributes.addFlashAttribute("flashKind", kind);
		redirectAttributes.addFlashAttribute("flashMessage", MyUtil.getMessage(messageKey));
		
	}
	
	private static MessageSource messageSource;
	private static String hostAndPort;
	private static String activeProfiles;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	
	@Value("${hostAndPort}")
	public void setHostAndPort(String hostAndPort){
		MyUtil.hostAndPort = hostAndPort;
	}
	
	public static String getActiveProfiles() {
		return activeProfiles;
	}
	
	@Value("${spring.profiles.active}")
	public void setActiveProfiles(String activeProfiles) {
		MyUtil.activeProfiles = activeProfiles;
	}
	
	public static boolean isDev(){
		logger.info("Active profiles are : " + activeProfiles);
		return activeProfiles.equals("dev");
	}
	
	public static String hostUrl(){
		return (isDev() ? "http://" : "https://") + hostAndPort;
	}
	
	public static String getMessage(String messageKey, Object... args) {
		return messageSource.getMessage(messageKey, args, Locale.getDefault());
	}
	
	public static void validate(boolean valid, String msgContent, Object... args) {
		// TODO Auto-generated method stub
		if(!valid)
			throw new RuntimeException(getMessage(msgContent, args));
	}
	
	public static User getSessionUser() {
		UserDetailsImpl auth = getAuth();
		return auth == null ? null : auth.getUser();
	}
	
	public static UserDetailsImpl getAuth() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
	    if (auth != null) {
	      Object principal = auth.getPrincipal();
	      if (principal instanceof UserDetailsImpl) {
	        return (UserDetailsImpl) principal;
	      }
	    }
	    return null;	  
	}
}
