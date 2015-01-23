package org.kns.timecard.common.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.kns.timecard.exception.TimecardUserNotFoundException;
import org.kns.timecard.frontend.timecarduser.dto.TimecardUserDto;
import org.kns.timecard.frontend.timecarduser.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


/**
 * Created by Jeevan on December 05, 2014 
 * 
 * Class to handle RememberMeAuthenticationSuccesscondition
 * @author KNS-ACCONTS
 *
 */
public class RememberMeAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private Logger log =Logger.getLogger(RememberMeAuthenticationSuccessHandler.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		String userName = authentication.getName();
		
		session.setAttribute("user", userName);
		Collection<GrantedAuthority> authorities=(Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		ArrayList<String >roles=new ArrayList<String>();
		for(GrantedAuthority auths :authorities){
			roles.add(auths.getAuthority());
		}
		TimecardUserDto userDto;
		try {
			userDto = this.userService.getTimeCardUserByEmailorUsername(userName);
			session.setAttribute("profilePic", userDto.getProfilePic());
			session.setAttribute("userId", userDto.getUserId());
		} catch (TimecardUserNotFoundException e) {
			
			e.printStackTrace();
		}
		
		session.setAttribute("roles", roles);		
	    super.setAlwaysUseDefaultTargetUrl(true);
	    super.setDefaultTargetUrl(request.getRequestURL().toString());
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	
}
