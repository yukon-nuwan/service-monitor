package com.yukon.service.monitor.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class NavigationController {
	private final static Logger logger = LoggerFactory.getLogger(NavigationController.class);



	/**
	 * Navigate to the home
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"","home"})
	String home(HttpServletRequest request) {
		return "home-tile";
	}
	
	
	/**
	 * Navigate to the caller
	 * @param request
	 * @return
	 */
	@RequestMapping("caller")
	String caller(HttpServletRequest request) {
		return "caller-tile";
	}
	
	
	/**
	 * Navigate to the service
	 * @param request
	 * @return
	 */
	@RequestMapping("service")
	String service(HttpServletRequest request) {
		return "service-tile";
	}
	
	
	/**
	 * Navigate to the caller service
	 * @param request
	 * @return
	 */
	@RequestMapping("callerService")
	String callerService(HttpServletRequest request) {
		return "callerService-tile";
	}
	
	

}