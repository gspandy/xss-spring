package com.ryanperrizo.spring.sample.cont;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ryanperrizo.spring.sample.services.UserService;
import com.ryanperrizo.spring.sample.util.MyUtil;

@Controller
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	
	//@Autowired
//	public UserController(UserService userService){ Annotation based injection
//		this.userService = userService;
//	}
	@Autowired // setter based injection
	public void setUserService(UserService userService){
		this.userService=userService;
	}
	@RequestMapping("/{verificationCode}/verify")
	public String verify(@PathVariable("verificationCode") String verificationCode,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request) throws ServletException{
		
		userService.verify(verificationCode);
		MyUtil.flash(redirectAttributes, "success", "verificationSuccess");
		request.logout();
		
		return "redirect:/";
	}
	
	@RequestMapping("/resend-verification-mail")
	public String reverify(RedirectAttributes redirectAttributes){
		
		userService.reverify();
		MyUtil.flash(redirectAttributes, "success", "verificationSent");
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/{userId}")
	public String getById(@PathVariable("userId") long userId, Model model){
		model.addAttribute("user", userService.findOne(userId));
		return "user";
	}
	
	

}
