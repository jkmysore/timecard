package org.kns.timecard.frontend.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.kns.timecard.frontend.user.dto.TimecardUserDto;
import org.kns.timecard.frontend.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 * Created onb June 23, 2014
 * Controller to handle all Login, Logout Operations
 *
 */

@Controller("loginController")
public class LoginController {
		
	private static Logger log=Logger.getLogger(LoginController.class);

	@Resource(name="userService")
	private UserService userService;
	
	/*
	 * Created by Jeevan on June 23, 2014
	 * Method to initiate Login Request..
	 * 
	 */
	@RequestMapping(value="/login.htm", method=RequestMethod.GET)
	public String initiateLogin(Map<String, Object> map){
		log.info("inside initiateLogin()");
		System.out.println("inside Controller");
		try{
			return "login";
		}
		catch(Exception e){
			log.error("Error While Inititing Login");
			String message="Error While Performing Login";
			map.put("map", message);
			map.put("title", message);
			return "error";
		}
	}
	
	
	
	
	/*
	 * Created by Jeevan on June 23, 2014
	 * Method to Redirect users to their Respective home pages based on Successful Login
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/home.htm", method=RequestMethod.GET)
	public String redirectUserstoHome(Map<String, Object> map,HttpServletRequest request){
		log.info("inside redirectUserstoHome");
		try{
			HttpSession session=request.getSession(true);
			String username=session.getAttribute("user").toString();
			ArrayList<String> roles=(ArrayList<String>) session.getAttribute("roles");
			TimecardUserDto userDto=this.userService.getTimeCardUserByUsernameorEmail(username);
			session.setAttribute("user_name", userDto.getFirstName()+" "+userDto.getLastName());
			session.setAttribute("profilePic", userDto.getProfilePic());
			session.setAttribute("userId", userDto.getUserId());
			//Home Page Redirection on the basis of Roles
			log.info("User "+username+" logged in at "+new Date());
			if(roles.contains("ROLE_SUPER_ADMIN")){				
				return "home/adminHome";				
			}
			else if(roles.contains("ROLE_SITE_ADMIN")){
				if(userDto.getIsFirstLogin()){
					map.put("userId",userDto.getUserId());
					map.put("firstVisit", true);
					map.put("userType", "SITE_ADMIN");
					return "redirect:/changepassword.htm";
				}
				return "home/siteAdminHome";
			}
			else if(roles.contains("ROLE_SUPER_MANAGER")){
				return "home/orgAdminManager";
			}
			else if(roles.contains("ROLE_EMPLOYEE")){
				return "home/employeeHome";
			}	
			else{
				return "/";
			}
		}
		catch(NullPointerException e){
			e.printStackTrace();
			log.error("Error While Redirecting User"+e.toString());
			String message="Error While Redirecting User";
			map.put("message", message);
			map.put("title", message);
			return "redirect:/";
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("Error While Redirecting User"+e.toString());
			String message="Error While Redirecting User";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
	}
	
	
	
	/*
	 * Created by Jeevan on July 04, 2014
	 * Method to handle First Time Visits  of a User.
	 * 
	 */
	@RequestMapping("/firstlogin.htm")
	public String handlePostFirstLoginConfigurations(Map<String, Object> map, @RequestParam("userId")Integer userId){
		log.info("handlePostFirstLoginConfigurations()");
		try{
			Integer updateResult=this.userService.handleFirstTimeVisitofUser(userId);
			if(updateResult>0){
				map.put("firstVisit", true);
				return "redirect:/home.htm";
			}
			else{
				throw new Exception();
			}
		}
		catch(Exception e){
			e.printStackTrace();
			String message="Error While Redirecting User to Home Page";
			log.error(message+" "+e.toString());
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
	}
	
	
	
	
	
	/*
	 * Created by Jeevan on June 26, 2014
	 * Method to handle Logout COndition
	 * Removes all cache and send to logout page
	 * 
	 */
	@RequestMapping(value="/logoutsuccess.htm", method=RequestMethod.GET)
	public String logoutHandler(Map<String, Object>map,HttpServletRequest request,HttpServletResponse response){
		log.info("inside logoutHandler()");
		try{
			
			HttpSession session=request.getSession(false);
			if(null!=session)
				session.invalidate();
			response.reset();
			   response.setHeader("Cache-Control", "no-cache");
			   response.setHeader("Pragma", "no-cache");
			    response.setHeader("Cache-Control", "no-store");
			   response.setHeader("Cache-Control", "must-revalidate");
			   response.setDateHeader("Expires", 0); 
			   String message="Successfully Logged Out";
			   map.put("message", message);			  
			  return "response";
		}
		catch(Exception e){
			log.info("User Logged Out"+ e.toString());
			e.printStackTrace();
			String message="Error While Logging out the User";
			   map.put("message", message);
			return "error";
		}
	}
	
	
	
	

	
	
	
	
	
	
}
