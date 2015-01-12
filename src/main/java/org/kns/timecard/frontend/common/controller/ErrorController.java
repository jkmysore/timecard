package org.kns.timecard.frontend.common.controller;

import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



/**
	 * 
	 * @author Jeevan -- KNS Technologies Corporation
	 * Created  on June 26, 2014
	 * Controller to handle all error conditions
	 *
	 */
	@Controller("errorController")
	public class ErrorController {
		
		private static Logger log=Logger.getLogger(ErrorController.class);
		
		/*
		 * Created on June 26, 2014
		 * Handling 404 error
		 */
		@RequestMapping("/404.htm")
		public String handlePageNotFoundError(Map<String, Object> map){
			System.out.println("INSIDE THOS");
			log.info("inside handlePageNotFoundError()");
			String message="Error 404, Request Page Not Found";
			map.put("message",message);
			map.put("title", message);
			return "error";
		}
		
		
		/*
		 * Created on June 26, 2014
		 * Handling 405 error
		 */
		
		@RequestMapping("/405.htm")
		public String methodNotSupportedError(Map<String, Object>map){
			log.info("MethodNotSupportedError()");
			String message="Error 405, Request Method Not Supported";
			map.put("message",message);
			map.put("title", message);
			return "error";
		}
		
		/*
		 * Created on June 26, 2014
		 * Handling 402 error
		 */
		
		@RequestMapping(value={"/401.htm","/403.htm"})
		public String unAuthorizedAccessError(Map<String, Object>map){
			log.info("unAuthorizedAccessError()");
			String message="Error 402, You are not authorized to access this page";
			map.put("message",message);
			map.put("title", message);
			return "error";
		}
		
		
		
}
