package com.privatecloud.web.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.privatecloud.users.dto.ResponseObject;
import com.privatecloud.users.dto.VMDto;
import com.privatecloud.users.dto.vmstat;
import com.privatecloud.users.model.Users;
import com.privatecloud.users.model.Vm;
import com.privatecloud.users.service.UsersService;
import com.privatecloud.users.service.VMService;

@Controller
public class MainController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private VMService vMService;

	private static Logger LOGGER = LoggerFactory.getLogger("MainController");

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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
	 
		List<Vm> vms = vMService.findAllVMsForUser(username);

		ModelAndView model = new ModelAndView();
		model.addObject("vms", vms);
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
		//		ArrayList<String>[][] a = new ArrayList[7][3];
		ArrayList<ArrayList<String>> a = new ArrayList<ArrayList<String>>();
		ArrayList<String> q = new ArrayList<>(0);

		LOGGER.info("statusPage");
		//	String shw = vMService.Showstats();
		ArrayList<VMDto> vmDtoList= vMService.Showstats();

		//		for(int j=0;j<x.size();j++){
		//		
		//		q.add(x.get(j).split(",").toString());
		//		for(int w=0;w<q.size();w++){
		//			
		//			a.get(j).add(w,q.get(w).toString());
		//		}
		//		}


		LOGGER.info(""+a);
		ModelAndView model = new ModelAndView();
		model.addObject("shw", vmDtoList);
		model.setViewName("status");
		return model;

	}


	//	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	//	public ModelAndView statsPage() {
	//
	//		LOGGER.info("statsPage");
	//		
	//		ModelAndView model = new ModelAndView();
	//		model.addObject("title", "HERE");
	//		model.addObject("message", "VM stats");
	//		model.setViewName("stats");
	//
	//		return model;
	//
	//	}


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


	@RequestMapping(value = "/vm", method = RequestMethod.GET)
	public ModelAndView listVMs() {

		List<Vm> vms = vMService.findAllVMs();

		ModelAndView model = new ModelAndView();
		model.addObject("vms", vms);
		model.setViewName("createVM");
		return model;

	}

	//	@RequestMapping(value = "/crvm", method = RequestMethod.GET)
	//	public ModelAndView create() {
	//
	//		String crv = vMService.Createvm();
	//		
	//		ModelAndView model = new ModelAndView();
	//		model.addObject("crv", crv);
	//		model.setViewName("VMcreated");
	//		return model;
	//
	//	}

	//	@RequestMapping(value = "/crvm", method = RequestMethod.POST)
	//	public String crvm(@ModelAttribute("crvm")Vm vmname, BindingResult result, ModelMap model) {
	//		
	//		if (result.hasErrors()) {
	//            //return "error";
	//			//TODO: Create a comman error page
	//		}
	//		
	//		
	//		vMService.Createvm();
	//		//usersService.registerUser(vmname);
	//		//fetch vm
	//		
	////		model.addAttribute("vmname", vmname.getVmname());
	//		
	//		return "VMcreated";
	//	}
	//	
	//	@RequestMapping(value = "/showstatus", method = RequestMethod.GET)
	//	public ModelAndView show() {
	//
	//		String shw = vMService.Showstats();
	//		
	//		ModelAndView model = new ModelAndView();
	//		model.addObject("shw", shw);
	//		model.setViewName("status");
	//		return model;
	//
	//	}

	@RequestMapping(value = "/status", method = RequestMethod.POST)
	public String showstatus(@ModelAttribute("status") ModelMap model) {

		//		if (result.hasErrors()) {
		//            //return "error";
		//			//TODO: Create a comman error page
		//		}
		//		

		ArrayList<VMDto> x= vMService.Showstats();
		//		String[] a=x.split(",");


		//usersService.registerUser(vmname);
		//fetch vm

		//  model.addAttribute("vmname", vmname.getVmname());

		//usersService.registerUser(vmname);
		//fetch vm


		return "status";
	}

	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public ModelAndView statsPage() {
		//		ArrayList<String>[][] a = new ArrayList[7][3];
		ArrayList<ArrayList<String>> a = new ArrayList<ArrayList<String>>();
		ArrayList<String> q = new ArrayList<>(0);

		LOGGER.info("statsPage");
		//	String shw = vMService.Showstats();
		ArrayList<vmstat> vmDtoList= vMService.sstats();




		LOGGER.info(""+a);
		ModelAndView model = new ModelAndView();
		model.addObject("sta", vmDtoList);
		model.setViewName("stats");
		return model;

	}


	@RequestMapping(value = "/stats", method = RequestMethod.POST)
	public String stats(@ModelAttribute("stats") ModelMap model) {

		//		if (result.hasErrors()) {
		//            //return "error";
		//			//TODO: Create a comman error page
		//		}
		//		

		vMService.sstats();
		//usersService.registerUser(vmname);
		//fetch vm

		//		model.addAttribute("vmname", vmname.getVmname());

		return "stats";
	}

	@RequestMapping(value = "/vm", method = RequestMethod.POST)
	public String vm(@ModelAttribute("vm")Vm vmname,  BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			//return "error";
			//TODO: Create a comman error page
		}

		vMService.createVM(vmname);

		vMService.Createvm(vmname.getVmname(), vmname.getOs());
		//usersService.registerUser(vmname);
		//fetch vm

		model.addAttribute("vmname", vmname.getVmname());
		model.addAttribute("os", vmname.getOs());

		return "createVM";
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

		//model.addAttribute("vmname", VM.getState());

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

	@RequestMapping(value = "/checkuname/{uname}", headers="Accept=application/json" , method = RequestMethod.GET)
	@ResponseBody
	public ResponseObject isUserNameAvailable(@PathVariable String uname) {
		ResponseObject res = new ResponseObject();
		res.setFlag(usersService.isUserNameAvailable(uname));
		return res;
	}
	
//	@ExceptionHandler(Exception.class)
//	public ModelAndView handleError(HttpServletRequest req, Exception exception) {
//		LOGGER.error("Request: " + req.getRequestURL() + " raised " + exception);
//
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("exception", exception);
//		mav.addObject("url", req.getRequestURL());
//		mav.setViewName("error");
//		return mav;
//	}

}