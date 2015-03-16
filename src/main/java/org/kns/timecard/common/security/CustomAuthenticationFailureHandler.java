package org.kns.timecard.common.security;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kns.timecard.exception.OrganizationNotActiveException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 * Created on June 20, 2014
 *  Custom Authentication Failure Adapter,
 * Handles Authentication Failure Conditions With Detailed Messages for Each Failure....
 *
 */

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	/* pre defined */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		super.onAuthenticationFailure(request, response, exception);
		String message;
		System.out.println("Exception "+exception.getMessage()+" "+exception.getClass());
		if(exception.getMessage().trim().equalsIgnoreCase("Account Expired")){
			message="Your Account is Expired, Please Contact Admin for further Details";
		}		
		else if(exception.getMessage().trim().equalsIgnoreCase("Account is Locked")){
			message="Your Account is Locked, Please Contact Admin for further Details";
		}
		else if(exception.getClass().isAssignableFrom(DisabledException.class)){
			message="Your Account is Disabled, Please Contact Admin for further Details";			
		}
		else if(exception.getMessage().trim().equalsIgnoreCase("Organization Not Found")){
			message="Your Organization is InActive,Please Contact Admin for further Details";
		}
		else{
			message="Invalid Login Credentials";
		}		
		request.getSession().setAttribute("error", message );       		
	}
	
	
	
}
