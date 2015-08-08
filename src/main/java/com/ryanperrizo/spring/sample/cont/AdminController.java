package com.ryanperrizo.spring.sample.cont;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ryanperrizo.spring.sample.dto.SearchForm;
import com.ryanperrizo.spring.sample.entities.Applicant;
import com.ryanperrizo.spring.sample.repositories.ApplicantRepository;
import com.ryanperrizo.spring.sample.services.AdminService;
import com.ryanperrizo.spring.sample.services.UserServiceImpl;

@Controller
public class AdminController {

	private ApplicantRepository applicantRepository;
	private AdminService adminService;
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
//	private SearchFormValidator searchFormValidator;
	
	@Autowired
	public AdminController(ApplicantRepository applicantRepository, AdminService adminService){
		this.applicantRepository = applicantRepository;
		this.adminService = adminService;
//		this.searchFormValidator = searchFormValidator;
	}
	
//	@InitBinder("searchForm") // Need to tell the controller to use this validator for the signupform
//	protected void initSearchBinder(WebDataBinder binder){
//		binder.setValidator(searchFormValidator);
//	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Model model){
		
		model.addAttribute("searchForm", new SearchForm());
		
		return "search";
	}
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@ModelAttribute("searchForm") @Valid SearchForm searchForm, BindingResult result,
			Model model){
		
		if (!result.hasErrors()){
			model.addAttribute("searchText", searchForm.getSearch());
			List<Applicant> applicants = applicantRepository.searchApplicants(searchForm.getSearch()); 
			model.addAttribute("applicants", applicants);
		}
		return "search";
	}
}
