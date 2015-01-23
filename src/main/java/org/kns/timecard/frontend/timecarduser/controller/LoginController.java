package org.kns.timecard.frontend.timecarduser.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.kns.timecard.frontend.timecarduser.dto.TimecardUserDto;
import org.kns.timecard.frontend.timecarduser.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author Jeevan
 * Created by Jeevan on September 11, 2014
 * Controller for Login Operations
 * It should include all Login, Home Redirection, First Login, Logout Operations
 *
 */

@Controller
public class LoginController {
	
	private static Logger log=Logger.getLogger(LoginController.class);
	
	
	@Resource(name="userService")
	private UserService userService;
	
	/**
	 * Created by Jeevan on September 11, 2014
	 * Method to initiate Login
	 * 
	 * @param map
	 * @return
	 */
	
	@RequestMapping(value="/login.htm", method=RequestMethod.GET)
	public String initiateLogin(Map<String, Object> map){
		log.info("inside initiateLogin()");
		System.out.println("inside Controller");
		try{
			return "login";
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("Error While Inititing Login");
			String message="Error While Performing Login";
			map.put("map", message);
			map.put("title", message);
			return "error";
		}
	}
	
	
	
	
	//Needs to handle home.htm
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/home.htm", method=RequestMethod.GET)
	public String redirectUserstoHome(Map<String, Object> map,HttpServletRequest request){
		log.info("inside redirectUserstoHome");
		try{
			HttpSession session=request.getSession(true);
			String username=session.getAttribute("user").toString();
			ArrayList<String> roles=(ArrayList<String>) session.getAttribute("roles");
			TimecardUserDto userDto=this.userService.getTimeCardUserByEmailorUsername(username);
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
		/*catch(EmployeeNotFoundException e){
			e.printStackTrace();
			log.error("Error While Redirecting User"+e.toString());
			String message="Employee Not Found while Redirecting User";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}*/
		catch(NullPointerException e){
			e.printStackTrace();
			log.error("Error While Redirecting User"+e.toString());
			String message="Error While Redirecting User";
			map.put("message", message);
			map.put("title", message);
			return "redirect:/login.htm";
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
