package com.ryanperrizo.spring.sample.cont;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.ryanperrizo.spring.sample.dto.InsecureSearchForm;
import com.ryanperrizo.spring.sample.dto.SearchForm;
import com.ryanperrizo.spring.sample.dto.SecureSearchForm;
import com.ryanperrizo.spring.sample.entities.Applicant;
import com.ryanperrizo.spring.sample.repositories.ApplicantRepository;
import com.ryanperrizo.spring.sample.services.AdminService;
import com.ryanperrizo.spring.sample.services.UserServiceImpl;

@Controller
public class AdminController {

	private ApplicantRepository applicantRepository;
	private AdminService adminService;
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	@Autowired
	public AdminController(ApplicantRepository applicantRepository, AdminService adminService){
		this.applicantRepository = applicantRepository;
		this.adminService = adminService;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam(required=false, value = "toggle") String toggle, Model model){
		if(toggle != null && toggle.equals("Secure")){
			model.addAttribute("secure", "Insecure");
			model.addAttribute("searchForm", new InsecureSearchForm());
			return "search";
		}
		model.addAttribute("searchForm", new SecureSearchForm());
		model.addAttribute("secure", "Secure");
		return "search";
	}
	
	@RequestMapping(value = "/search", params = "Secure", method = RequestMethod.POST)
	public String search(@RequestParam(required=false, value="Secure") String secure, 
			@ModelAttribute("searchForm") @Valid SecureSearchForm searchForm, BindingResult result,
			Model model){
		
			if (!result.hasErrors()){
				model.addAttribute("searchText", searchForm.getSearch());
				List<Applicant> applicants = applicantRepository.searchApplicants(searchForm.getSearch()); 
				model.addAttribute("applicants", applicants);
			}
			model.addAttribute("secure", "Secure");

			return "search";
	}
	
	@RequestMapping(value = "/search", params = "Insecure", method = RequestMethod.POST)
	public String InsecureSearch(@ModelAttribute("searchForm") InsecureSearchForm searchForm, Model model){
			
			model.addAttribute("searchText", searchForm.getSearch());
			List<Applicant> applicants = applicantRepository.searchApplicants(searchForm.getSearch()); 
			model.addAttribute("applicants", applicants);
			model.addAttribute("secure", "Insecure");
			
			return "search";
	}
}
