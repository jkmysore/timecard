package org.kns.timecard.common.security;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.kns.timecard.exception.OrganizationNotActiveException;
import org.kns.timecard.exception.OrganizationNotFoundException;
import org.kns.timecard.exception.TimecardUserNotFoundException;
import org.kns.timecard.frontend.organization.organization.dto.OrganizationDto;
import org.kns.timecard.frontend.organization.organization.service.OrganizationService;
import org.kns.timecard.frontend.timecarduser.dto.RolesDto;
import org.kns.timecard.frontend.timecarduser.dto.TimecardUserDto;
import org.kns.timecard.frontend.timecarduser.service.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 * 
 * Created by Jeevan on June 19, 2014
 * Class to handle Security and User Authentication
 *
 */
@SuppressWarnings("deprecation")
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="organizationService")
	private OrganizationService organizationService;
	
	
	
	private static Logger log=Logger.getLogger(UserDetailServiceImpl.class);
	
	/**
	 * Ennumneration for User Roles
	 * All USer ROles hierarchy will be created in the ENUM
	 */
	public enum UserRoles {
	     ROLE_ANONYMOUS(new String[]{"ROLE_ANONYMOUS"}),
	     ROLE_EMPLOYEE(new String[]{"ROLE_EMPLOYEE"}),
	     ROLE_SITE_MANAGER(new String[]{"ROLE_EMPLOYEE","ROLE_SITE_MANAGER"}),
	     ROLE_SITE_ADMIN(new String[]{"ROLE_EMPLOYEE","ROLE_SITE_MANAGER","ROLE_SITE_ADMIN"}),
	     ROLE_SUPER_ADMIN(new String[]{"ROLE_EMPLOYEE","ROLE_SITE_MANAGER","ROLE_SITE_ADMIN","ROLE_SUPER_ADMIN"});
	   
	     private final String[] roles;

	     private UserRoles(String[] roles) {
	          this.roles = roles;
	     }
	     public String[] getRoles() {
	          return roles;
	     }
	}
	
	
	
	
	/* *Created by Jeevan on June 20, 2014
	 * Overriden method to Authenticate User
	 * 
	 *  Need to perform Only Authentication here, all the errors must be thrown in Authentication Success
	 *  
	 *  After much thought, decided it implemented it here itseld rather than on AuthenticationSuccessHandler
	 */ 
	 
	

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		System.out.println("CALLED");
		log.info("inside loadUserByUsername()");
		TimecardUserDto userEntity=null;
		ArrayList<OrganizationDto> organizationDto=null;
		try{
			userEntity=this.userService.getTimeCardUserByEmailorUsername(username);
			 organizationDto=this.organizationService.getOrganizationsByAdminorManager(userEntity.getTimeCardCredentials().getEmail());
		}
		catch(TimecardUserNotFoundException e){
			log.error("Failed to get User by Username "+e.toString());
		} 
		catch (OrganizationNotFoundException e) {
			log.error("Failed to get Organization by Email "+e.toString());
		}
		
		if (userEntity == null){
	    	throw new UsernameNotFoundException("user not found");
		}
		
		
				
		boolean flag=true;		
		boolean enabled=flag;
		boolean accountNonExpired=flag;
		boolean credentialsNonExpired=flag;
		boolean accountNonLocked;
		
		// Checking Account Active Status 
		if(!userEntity.getIsActive()){
			accountNonExpired=false;
			throw new CredentialsExpiredException("Account is Expired"); 
		}
		else{
			accountNonLocked=true;
		}
		
		
		Date date=new Date();
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);		
		// Checking Account Subscription Status 
		if(null!=userEntity.getAccountExpiryDate() && date.after(userEntity.getAccountExpiryDate())){
			credentialsNonExpired=false;
			try{
				//Need to set account Expired == true
			}
			catch(Exception e){
				
			}
			throw new CredentialsExpiredException("Account Expired", null);
		}	
		
		
		// Checking Account Lock Status 
		if(userEntity.getIsLocked()){
			accountNonLocked=false;
			throw new LockedException("Account is Locked"); 
		}
		else{
			accountNonLocked=true;
		}
		
		// Handling User Role 
		//Handling The Organization Status Of Active ,added By bhagya On Feb24th,2015
			
		 Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		 for(RolesDto roleDto:userEntity.getTimeCardCredentials().getRole()){
			 if(!roleDto.getRoleName().equalsIgnoreCase("ROLE_SUPER_ADMIN")){
				 for(OrganizationDto orgDto:organizationDto){
					 ArrayList<OrganizationDto> organizationDtos=new ArrayList<OrganizationDto>();
					 	 	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM/dd/yyyy");
							String statusDate=simpleDateFormat.format(orgDto.getStatusDate());
							String currentDate=	 simpleDateFormat.format(new Date());
							if(orgDto.getIsActive() && orgDto.getStatus().equalsIgnoreCase("block")){
								if(statusDate.equalsIgnoreCase(currentDate)){
									orgDto.setIsActive(false);
							 		organizationDtos.add(orgDto);
							 		try{
							 		 this.organizationService.saveActivationDetailsOfOrganization(organizationDtos);
							 		}
							 		catch(OrganizationNotFoundException e){
							 			log.error("Failed to get Organization "+e.toString());
							 		} catch (Exception e) {
							 			log.error("Exception While getting Organization "+e.toString());
										
									}
							 	}
						 }
						 else if(!orgDto.getIsActive() && orgDto.getStatus().equalsIgnoreCase("allow")){
								if(statusDate.equalsIgnoreCase(currentDate)){
										orgDto.setIsActive(true);
						 				organizationDtos.add(orgDto);
						 				try{
						 				 this.organizationService.saveActivationDetailsOfOrganization(organizationDtos);
						 				}
						 				catch(OrganizationNotFoundException e){
						 					log.error("Failed to get Organization  "+e.toString());
						 				} 
						 				catch (Exception e) {
						 					log.error("Exception While getting Organization "+e.toString());
											
										}
						 		}
							
						}
						
						if(!orgDto.getIsActive()){
						 throw new OrganizationNotActiveException("Organization Not Found");
						}
				}
			}
				 //
			 
			 for(String role: UserRoles.valueOf(roleDto.getRoleName()).getRoles()){
				 authorities.add(new GrantedAuthorityImpl(role));
			 }
		 }
		//  Performing Actual Authentication 		 
		 User user=new User(userEntity.getTimeCardCredentials().getEmail(), userEntity.getTimeCardCredentials().getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		 return (UserDetails)user;
	}
	
}
