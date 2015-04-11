package com.privatecloud.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.VM;

import com.privatecloud.constants.AppConstants;
import com.privatecloud.users.model.UserRoles;
import com.privatecloud.users.model.Users;
import com.privatecloud.users.service.UsersService;

@Controller
public class MainController {

	@Autowired
	private UsersService usersService;
	
	Logger LOGGER = LoggerFactory.getLogger("MainController");
	
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		LOGGER.info("defaultPage");
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security + Hibernate Example");
		model.addObject("message", "This is default page!");
		model.setViewName("login");
		return model;

	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage() {

		LOGGER.info("homePage");
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security + Hibernate Example");
		model.addObject("message", "This is default page!");
		model.setViewName("home");
		return model;

	}

	@RequestMapping(value = "/createVM", method = RequestMethod.GET)
	public ModelAndView createVMPage() {

		LOGGER.info("createPage");
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "HERE");
		model.addObject("message", "create VM page");
		model.setViewName("createVM");

		return model;

	}
	
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public ModelAndView statusPage() {

		LOGGER.info("statusPage");
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "HERE");
		model.addObject("message", "VM status");
		model.setViewName("status");

		return model;

	}
	
	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public ModelAndView statsPage() {

		LOGGER.info("statsPage");
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "HERE");
		model.addObject("message", "VM stats");
		model.setViewName("stats");

		return model;

	}
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security + Hibernate Example");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");

		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView showSignupPage() {

		ModelAndView model = new ModelAndView();
		model.setViewName("signup");

		return model;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("user")Users user, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
            //return "error";
			//TODO: Create a comman error page
		}
				
		usersService.registerUser(user);
		//fetch vm
		
		model.addAttribute("vmname", VM.getState());
		
		return "login";
	}
	
	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}

}