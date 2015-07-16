package com.ryanperrizo.spring.sample.cont;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ryanperrizo.spring.sample.dto.CommentForm;
import com.ryanperrizo.spring.sample.dto.SignupForm;
import com.ryanperrizo.spring.sample.mail.MailSender;
import com.ryanperrizo.spring.sample.mail.MockMailSender;
import com.ryanperrizo.spring.sample.services.UserService;
import com.ryanperrizo.spring.sample.util.MyUtil;
import com.ryanperrizo.spring.sample.validators.SignupFormValidator;

@Controller
public class RootController {
	
	private MailSender mailSender;
	private UserService userService;
	private SignupFormValidator signupFormValidator;
	
	private static Logger logger = LoggerFactory.getLogger(RootController.class);
	
	@Value("${app.name}")
	private String appName;
	
	@Autowired
	public RootController(MailSender mailSender, UserService userService,
			SignupFormValidator signupFormValidator){
		this.mailSender = mailSender;
		this.userService = userService;
		this.signupFormValidator = signupFormValidator;
	}
	
	@InitBinder("signupForm") // Need to tell the controller to use this validator for the signupform
	protected void initSignupBinder(WebDataBinder binder){
		binder.setValidator(signupFormValidator);
	}
	
//	@RequestMapping("/")
//	public String hello() throws MessagingException {
//		logger.info("Running Hello Method");
//		mailSender.send("perrizotest@gmail.com", "Email from Spring", "This is the test message" + "${app.name}");
//		return "home";
//	}
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String singup(Model model){
		model.addAttribute("signupForm", new SignupForm());//default key would be the same
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("signupForm") @Valid SignupForm signupForm, BindingResult result,
			RedirectAttributes redirectAttributes){
		
		if (result.hasErrors())
			return "signup";
		
		userService.signup(signupForm);
		
		MyUtil.flash(redirectAttributes, "success", "signupSuccess");

		return "redirect:/";
	}
	
	@RequestMapping(value = "/comments", method = RequestMethod.GET)
	public String comments(Model model){
		model.addAttribute("commentForm", new CommentForm());
		model.addAttribute("comments", userService.viewComments());
		return "comment";
	}
	
	@RequestMapping(value = "/comments", method = RequestMethod.POST)
	public String comments(@ModelAttribute("commentForm") @Valid CommentForm commentForm){
		
		userService.addComment(commentForm);
		
		return "redirect:/comments";
	}
}
