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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ryanperrizo.spring.sample.dto.ApplicantForm;
import com.ryanperrizo.spring.sample.dto.CommentForm;
import com.ryanperrizo.spring.sample.dto.ForgotPasswordForm;
import com.ryanperrizo.spring.sample.dto.InsecureApplicantForm;
import com.ryanperrizo.spring.sample.dto.InsecureCommentForm;
import com.ryanperrizo.spring.sample.dto.SearchForm;
import com.ryanperrizo.spring.sample.dto.SecureApplicantForm;
import com.ryanperrizo.spring.sample.dto.SecureCommentForm;
import com.ryanperrizo.spring.sample.dto.SignupForm;
import com.ryanperrizo.spring.sample.mail.MailSender;
import com.ryanperrizo.spring.sample.mail.MockMailSender;
import com.ryanperrizo.spring.sample.services.UserService;
import com.ryanperrizo.spring.sample.util.MyUtil;
import com.ryanperrizo.spring.sample.validators.ForgotPasswordFormValidator;
import com.ryanperrizo.spring.sample.validators.SignupFormValidator;

@Controller
public class RootController {
	
	private MailSender mailSender;
	private UserService userService;
	private SignupFormValidator signupFormValidator;
	private ForgotPasswordFormValidator forgotPasswordFormValidator;
	
	private static Logger logger = LoggerFactory.getLogger(RootController.class);
	
	@Value("${app.name}")
	private String appName;
	
	
	@Autowired
	public RootController(MailSender mailSender, UserService userService,
			SignupFormValidator signupFormValidator, 
			ForgotPasswordFormValidator forgotPasswordFormValidator){
		this.mailSender = mailSender;
		this.userService = userService;
		this.signupFormValidator = signupFormValidator;
		this.forgotPasswordFormValidator = forgotPasswordFormValidator;
	}
	
	@InitBinder("signupForm") // Need to tell the controller to use this validator for the signupform
	protected void initSignupBinder(WebDataBinder binder){
		binder.setValidator(signupFormValidator);
	}
	
	@InitBinder("forgotPasswordForm") // Need to tell the controller to use this validator for the signupform
	protected void initForgotPasswordBinder(WebDataBinder binder){
		binder.setValidator(forgotPasswordFormValidator);
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
	public String comments(@RequestParam(required=false, value="toggle") String toggle, Model model){
		if(toggle != null && toggle.equals("Secure")){
			model.addAttribute("secure", "Insecure");
			model.addAttribute("commentForm", new InsecureCommentForm());
			model.addAttribute("comments", userService.viewComments());
			return "comment";
		}
		model.addAttribute("secure", "Secure");
		model.addAttribute("commentForm", new SecureCommentForm());
		model.addAttribute("comments", userService.viewComments());
		return "comment";
	}
	
	@RequestMapping(value = "/comments", params="Secure", method = RequestMethod.POST)
	public String comments(@ModelAttribute("commentForm") @Valid SecureCommentForm commentForm, BindingResult result,
			Model model){
		
		if(!result.hasErrors())
			userService.addComment(commentForm);
		
		model.addAttribute("secure", "Secure");
		model.addAttribute("comments", userService.viewComments());
		return "comment";
	}
	@RequestMapping(value = "/comments", params="Insecure", method = RequestMethod.POST)
	public String comments(@ModelAttribute("commentForm") @Valid InsecureCommentForm commentForm,
			Model model){
		
		userService.addComment(commentForm);
		model.addAttribute("secure", "Insecure");
		model.addAttribute("comments", userService.viewComments());
		return "comment";
	}
	
	@RequestMapping(value = "/apply", method = RequestMethod.GET)
	public String apply(@RequestParam(required=false, value="toggle") String toggle, Model model){
		if(toggle != null && toggle.equals("Secure")){
			model.addAttribute("secure", "Insecure");
			model.addAttribute("applicantForm", new InsecureApplicantForm());
			return "apply";
		}
		model.addAttribute("secure", "Secure");
		model.addAttribute("applicantForm", new SecureApplicantForm());
		return "apply";
	}
	
	@RequestMapping(value = "/apply", params = "Secure",  method = RequestMethod.POST)
	public String secureApply(@ModelAttribute("applicantForm") @Valid SecureApplicantForm applicantForm,
			BindingResult result,
			RedirectAttributes redirectAttributes, Model model){
		
		if(result.hasErrors()){
			model.addAttribute("secure", "Secure");
			return "apply";
		}
		
		MyUtil.flash(redirectAttributes, "success", "applySuccess");
		userService.apply(applicantForm);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/apply", params = "Insecure",  method = RequestMethod.POST)
	public String insecureApply(@ModelAttribute("applicantForm") SecureApplicantForm applicantForm,
			RedirectAttributes redirectAttributes){

		MyUtil.flash(redirectAttributes, "success", "applySuccess");
		userService.apply(applicantForm);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
	public String forgotPassword(Model model){
		model.addAttribute(new ForgotPasswordForm());//default key would be the same
		return "forgot-password";
	}
	
	@RequestMapping(value = "/forgot-password",  method = RequestMethod.POST)
	public String forgotPassword(@ModelAttribute("forgotPasswordForm") @Valid ForgotPasswordForm forgotPasswordForm,
			BindingResult result,
			RedirectAttributes redirectAttributes, Model model){
		
		if(result.hasErrors())
			return "forgot-password";
		
		userService.forgotPassword(forgotPasswordForm);
		MyUtil.flash(redirectAttributes, "success", "checkMailResetPassword");
		
		return "redirect:/";
	}
}
