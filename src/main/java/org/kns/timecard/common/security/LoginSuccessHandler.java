package org.kns.timecard.common.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 *
 *	Created by Jeevan on June 20, 2014
 *	
 *  Class to Handle Authentication Success Condition.
 *  
 *
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	
	/*
	 * Overriden method
	 * Adds User and Role to Session on Successful Login	 * 
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {		
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();			
		String user=auth.getName();	
		HttpSession session=request.getSession();
		session.setAttribute("user", user);
		Collection<GrantedAuthority> authorities=(Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		ArrayList<String >roles=new ArrayList<String>();
		for(GrantedAuthority auths :authorities){
			roles.add(auths.getAuthority());
		}
		session.setAttribute("roles", roles);		
		 SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
         if(savedRequest != null) {
             response.sendRedirect(savedRequest.getRedirectUrl());
         }else{
            response.sendRedirect(request.getContextPath()+"/home.htm");
         }
	}
}
