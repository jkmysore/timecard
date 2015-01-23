package org.kns.timecard.frontend.timecarduser.controller;

import java.net.ConnectException;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.kns.timecard.exception.MailNotSendException;
import org.kns.timecard.exception.PasswordTokenExpiedException;
import org.kns.timecard.exception.TimecardUserNotFoundException;
import org.kns.timecard.exception.PasswordNotMatchedException;
import org.kns.timecard.frontend.timecarduser.dto.TimecardUserDto;
import org.kns.timecard.frontend.timecarduser.service.UserService;
import org.kns.timecard.frontend.timecarduser.dto.ChangePasswordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
 * @author Jeevan -- KNS TECHNOLOGIES CORPORTATION
      Created on 17-Sep-2014 4:47:19 pm
 *  Created by Jeevan
 *  Registration Controller handles all User Registration, Forgot Pwd, Reset Pwd etc
 */

@Controller
public class RegistrationController {

	
	private static Logger log=Logger.getLogger(RegistrationController.class);
	
	
	@Resource(name="userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("changePasswordValidator")
	private Validator validator;
	
	@InitBinder("changePassword")
	protected void initUserBinder(WebDataBinder binder) {
	    binder.setValidator(validator);
	}
	/**
	 * Controller should include the following Details
	 * 
	 * 1. User Registration if needed
	 * 2. Forgot pwd
	 * 3. Reset Pwd
	 * 4. Profile Update
	 * 5. Change Pwd
	 * 
	 * 
	 */
	

	
 /*********************Forget Password **********************************************/
	
	/**
	 * 
	 * Created by Jeevan on 17-Sep-2014 4:50:24 pm
		Method to initiate Forgot Password process
		@param map
		@return
	 */
	@RequestMapping(value="/forgetpassword.htm",method=RequestMethod.GET)
	public String initiateForgotPassword(Map<String, Object> map){
		log.info("inside initiateFogotPassword()");
		try{
			String title="Forgot Password Page- Timecard";
			map.put("title", title);
			return "forgetPassword";
		}
		catch(Exception e){
			String message="Error While Initiating Forget Password";
			map.put("message", message);
			map.put("title",message);
			return "error";
		}
	}
	
	
	/**
	 * 
	 * Created by Jeevan on 17-Sep-2014 6:15:00 pm
		
		@param email
		@param map
		@return
	 *
	 *  Method to send Forget Password Mail	 *
	 */
	@RequestMapping(value="/forgetpassword.htm",method=RequestMethod.POST)
	public String processForgetPassword(@RequestParam("email") String email, Map<String, Object> map){
		log.info("inside processForgetPassword()");
		try{
			@SuppressWarnings("unused")
			Integer result=this.userService.sendPasswordResetMail(email);
			String message="Password rest link has been sent to your registered email, please follow the instructions to reset password";
			map.put("message", message);
			map.put("title", message);
			return "response";
		}
		catch(MailNotSendException e ){
			log.error("Failed to Send Mail");
			String message="Password Reset Link Error";
			String title="Failed to Send Email with Password Reset Link";
			map.put("title", title);
			map.put("message", message);
			e.printStackTrace();
			return "response";
		}
		catch(ConnectException e){
			log.error("Failed to Send Mail");
			String message="Password Reset Link Error";
			String title="Failed to Send Email with Password Reset Link";
			map.put("title", title);
			map.put("message", message);
			e.printStackTrace();
			return "response";
		}	
		catch(Exception e){
			log.error("Error in Password Reset");
			String title="Error while initiating Password Reset";
			String message="Failed to generate Password Reset Link";
			map.put("title", title);
			map.put("message", message);
			e.printStackTrace();
			return "response";
		}
	}
	
	
	/**
	 * 
	 * Created by Jeevan on 17-Sep-2014 5:07:27 pm		
	 *	@param email
	 *	@return
	 *
	 *	Method to validate existance of email ID
	 */	
	@SuppressWarnings("finally")
	@RequestMapping(value="/validatemail.htm",method=RequestMethod.GET)
	@ResponseBody
	public String checkIfEmailExists(@RequestParam("email") String email){
		log.info("inside checkIfEmailExists()");		
		String emailResult="false";
		try{
			TimecardUserDto timecardUserDto=this.userService.getTimecardUserByEmail(email);
			if(null!=timecardUserDto){
				emailResult="true";				
			}
		}
		catch(TimecardUserNotFoundException e){
			log.error("Time Card User Not Found "+e.toString());			
		}
		catch(Exception e){
			log.error("Exception occured while validating password "+e.toString());			
		}
		finally{
			return emailResult;
		}
	}
	
/**********************************************END OF Forget Password ******************************************************************/
	
	
	
	

/************************************************RESET PASSWORD **************************************************************************/

	/**
	 *Created By Bhagya on 14-oct-2014 1:18:07pm
	 * @param token
	 * @return
	 * 
	 * Method to initiating the Reset password
	 */
	@RequestMapping(value="/resetpassword.htm",method=RequestMethod.GET)
	public String initiateResetPassword(@RequestParam("token") String passwordToken,Map<String, Object>map){
		log.info("inside initiateResetPassword()");
		try{
			Integer userCredentialId=this.userService.getTimecardUserCredentialsIdbyPasswordToken(passwordToken);
			map.put("userCredentialId", userCredentialId);
			map.put("title", "Reset Password");
			return "resetPassword";
		}
		catch(TimecardUserNotFoundException e){
			log.error("User Not Found "+e.toString());
			String message="No User Found, Please check the link";
			map.put("message", message);
			map.put("title", message);
			e.printStackTrace();
			return "error";
		}
		catch(Exception e){
			log.error("Error While initiating Reset Password "+e.toString());
			String message="Error while initiating reset password";
			map.put("title", message);
			map.put("message", message);
			e.printStackTrace();
			return "error";
		}
	}
	
	/**
	 * Created By Bhagya on 14-10-2014 1:33:34 pm
	 * @param userCredentialId,password
	 * @return
	 * 
	 * Method For Process The  Reset the password
	 */
	@RequestMapping(value="/resetpassword.htm",method=RequestMethod.POST)
	public String processPasswordReset(@RequestParam("userCredentialId")Integer userCredentialId,@RequestParam("password")String password, Map<String, Object> map){
		log.info("inside processPasswordReset()");
		try{
			System.out.println("USER ID "+userCredentialId);
			Integer result=this.userService.handlePasswordReset(userCredentialId, password);
			String message="Password Reset Successfully, Please Login to Continue..";
			map.put("message", message);
			map.put("title", message);
			return "response";
		}
		catch(TimecardUserNotFoundException e){
			log.error("No User Found with the Token "+e.toString());
			String message="No User Found with the Token";
			map.put("message", message);
			map.put("title", message); e.printStackTrace();
			return "error";
		}
		catch(PasswordTokenExpiedException e){
			String message="Password Token Expired, Please Try With Forget Password Again";
			log.error(message+e.toString());
			map.put("message", message);
			map.put("title", message); e.printStackTrace();
			return "error";
		}
		catch(Exception e){
			String message="Error while processing reset password";
			log.error(message+e.toString());
			map.put("message", message);
			map.put("title", message); e.printStackTrace();
			return "error";
		}
	}
	

/************************************************END OF RESET PASSWORD ********************************************************************************/
	
	
/*************************************************CHANGE PASSWORD**************************************************************************************/
	/**
	 * Created By Bhagya on OCtober 21st,2014
	 * @param userid,firstVisit,userType,changepasswordDto
	 * @return changePassword
	 * 
	 * Method For perform Change Password after Login
	 * 
	 * Accepts addiitonal parameters which comes handy while redirecting user on first Login
	 */
	
	@RequestMapping(value="/changepassword.htm",method=RequestMethod.GET)
	public String initChangePasswordofUser(@RequestParam("userId")Integer userId,@RequestParam(value="firstVisit",required=false)Boolean firstVisit,
			@RequestParam(value="userType",required=false)String userType,Map<String, Object> map,@ModelAttribute("changePassword") ChangePasswordDto changePassword){
		log.info("inside initChangePassword()");
		try{
			map.put("userId",userId);
			map.put("firstVisit", firstVisit);
			map.put("userType", userType);
			return "home/changePassword";		   //get 	
		}
		catch(Exception e){
			String message="Error While Initiating Reset Password";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
	}
	
	/**
	 * Created By Bhagya on OCtober 21st,2014
	 * @param userType,changePasswordDto,firstVisit
	 * @return configuration
	 * 
	 * POST Method For perform Change Password after Login
	 * 
	 * 
	 */
	@RequestMapping(value="/changepassword.htm",method=RequestMethod.POST)
	public String processChangePassword(@RequestParam(value="userType",required=false)String userType, @Valid @ModelAttribute("changePassword")ChangePasswordDto changePassord, BindingResult result,Map<String, Object> map, @RequestParam(value="firstVisit",required=false)Boolean firstVisit
			){		
		log.info("inside processChangePassword()");
		try{
			if(result.hasErrors()){
				map.put("firstVisit", firstVisit);
				return "home/changePassword";
			}
			Integer updateResult=this.userService.performPasswordChange(changePassord);
			System.out.println("UPDATE RESUT"+updateResult);
			if(updateResult>0){	
					if(userType.equals("SITE_ADMIN")){
					System.out.println("TO COnfiguratdion"+firstVisit);
					map.put("firstVisit",firstVisit);
					map.put("userId", changePassord.getUserId());
					return "redirect:/organization/configuration.htm";
				}
				else{					
					return "redirect:/home.htm";
				}
			}
			else{				
				throw new Exception();
			}			
		}
		catch(PasswordNotMatchedException e){
			e.printStackTrace();
			log.error("PAssword Entered do not match "+e.toString());
			map.put("message", "Password entered does not match with existing password");
			map.put("title", "Password entered does not match with existing password");
			map.put("firstVisit", firstVisit);
			map.put("changePassword",changePassord);
			map.put("userType", userType);
			map.put("userId",changePassord.getUserId());
			return "home/changePassword";
		}
		catch(TimecardUserNotFoundException e){
			e.printStackTrace();
			String message="Error While Changing Password, No User Found";
			map.put("title", message);
			log.error(message+" "+e.toString());
			map.put("message", message);
			map.put("userId",changePassord.getUserId());
			return "error";
		}
		catch(Exception e){
			e.printStackTrace();
			String message="Error While Changing Password";
			map.put("title", message);
			log.error(message+" "+e.toString());
			map.put("message", message);
			map.put("userId",changePassord.getUserId());
			return "error";
		}
		
	}
	
}
