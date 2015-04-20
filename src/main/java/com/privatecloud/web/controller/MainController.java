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
import com.privatecloud.users.dto.VMStatsDTO;
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

	private String getUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    return auth.getName();
	}
	
	
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
	 
		List<Vm> vms = vMService.findAllVMsForUser(getUserName());

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
		return homePage();

	}

	@RequestMapping(value = "/status", method = RequestMethod.POST)
	public String showstatus(@ModelAttribute("status") ModelMap model) {	

		ArrayList<VMDto> x= vMService.Showstats();
		
		return "status";
	}

	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public ModelAndView statsPage() {

		LOGGER.info("statsPage");
		
		//ArrayList<VMStatsDTO> vmDtoList= vMService.sstats();
		
		ArrayList<VMStatsDTO> vmDtoList = vMService.getVMStats(getUserName());
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

	/**
	 * This method is used to create a virtual machine
	 * */
	@RequestMapping(value = "/vm", method = RequestMethod.POST)
	public ModelAndView vm(@ModelAttribute("vm")Vm vmname,  BindingResult result, ModelMap model) { 
		vmname.setUsername(getUserName());
		vMService.createVM(vmname);

		model.addAttribute("vmname", vmname.getVmname());
		model.addAttribute("os", vmname.getOs());

		return homePage();
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
		LOGGER.info("Start: MainController.isUserNameAvailable");
		ResponseObject res = new ResponseObject();
		res.setFlag(usersService.isUserNameAvailable(uname));
		LOGGER.info("End: MainController.isUserNameAvailable");
		return res;
	}
	
	@RequestMapping(value = "/powerOn/{vmname}", headers="Accept=application/json" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject powerOn(@PathVariable String vmname) {
		LOGGER.info("Start: MainController.powerOn");
		ResponseObject res = new ResponseObject();
		res.setFlag(vMService.powerOn(vmname));
		LOGGER.info("End: MainController.powerOn");
		return res;
	}
	
	@RequestMapping(value = "/powerOff/{vmname}", headers="Accept=application/json" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject powerOff(@PathVariable String vmname) {
		LOGGER.info("Start: MainController.powerOff");
		ResponseObject res = new ResponseObject();
		res.setFlag(vMService.powerOff(vmname));
		LOGGER.info("End: MainController.powerOff");
		return res;
	}
	
	@RequestMapping(value = "/destroyVM/{vmname}", headers="Accept=application/json" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject destroyVM(@PathVariable String vmname) {
		LOGGER.info("Start: MainController.destroyVM");
		ResponseObject res = new ResponseObject();
		res.setFlag(vMService.destroyVM(vmname));
		LOGGER.info("End: MainController.destroyVM");
		return res;
	}
	
	@RequestMapping(value = "/checkvname/{vname}", headers="Accept=application/json" , method = RequestMethod.GET)
	@ResponseBody
	public ResponseObject isVmNameAvailable(@PathVariable String vname) {
		LOGGER.info("Start: MainController.isVmNameAvailable");
		ResponseObject resi = new ResponseObject();
		resi.setFlag(vMService.isVmNameAvailable(vname));
		LOGGER.info("End: MainController.isVmNameAvailable");
		return resi;
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