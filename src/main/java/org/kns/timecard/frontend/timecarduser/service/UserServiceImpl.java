package org.kns.timecard.frontend.timecarduser.service;

import java.util.Calendar;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.kns.timecard.backend.timecarduser.dao.UserDao;
import org.kns.timecard.backend.timecarduser.exception.MailNotSendException;
import org.kns.timecard.backend.timecarduser.exception.PasswordTokenExpiedException;
import org.kns.timecard.backend.timecarduser.exception.TimecardUserNotFoundException;
import org.kns.timecard.backend.timecarduser.model.TimeCardUserCredentials;
import org.kns.timecard.backend.timecarduser.model.TimecardUser;
import org.kns.timecard.backend.timecarduser.exception.PasswordNotMatchedException;
import org.kns.timecard.backend.timecarduser.model.TimecardUser;
import org.kns.timecard.frontend.common.utility.EmailSender;
import org.kns.timecard.frontend.timecarduser.dto.TimecardUserDto;
import org.kns.timecard.frontend.timecarduser.dto.ChangePasswordDto;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * @author Jeevan -- KNS TECHNOLOGIES CORPORTATION
      Created on 17-Sep-2014 1:21:58 pm
 *  Service for User Related functions
 */

@Service("userService")
public class UserServiceImpl implements UserService {

	private static Logger log=Logger.getLogger(UserServiceImpl.class);
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="emailSender")
	private EmailSender emailSender;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	/**
	 * 
	 * Created by Jeevan on 17-Sep-2014 1:33:57 pm
		Method to get Time card user by Logged in Criteria
		@param username
		@return
		@throws TimecardUserNotFoundException
	 */
	public TimecardUserDto getTimeCardUserByEmailorUsername(String username)throws TimecardUserNotFoundException{
		log.info("inside getTimecardUserByEmailorUsername");
		TimecardUser timecardUser=this.userDao.getTimecardCredentialsForLogin(username);
		TimecardUserDto timecardUserDto=TimecardUserDto.populateTimeCardUser(timecardUser);
		return timecardUserDto;
	}
	
	
	/**
	 * 
	 * Created by Jeevan on 17-Sep-2014 5:18:03 pm
		
		@param email
		@return
		@throws TimecardUserNotFoundException
		Method to get Timecard user by email
	 */
	public TimecardUserDto getTimecardUserByEmail(String email)throws TimecardUserNotFoundException{
		log.info("inside getTimecardUserByEmail()");
		TimecardUser timecardUser=this.userDao.getTimecardUserByEmail(email);
		TimecardUserDto timecardUserDto=TimecardUserDto.populateTimeCardUser(timecardUser);
		return timecardUserDto;
	}
	
	
	
	/**
	 * 
	 * Created by Jeevan on 17-Sep-2014 5:49:18 pm
		
		@param email
		@return
		@throws Exception
	 *
	 *	Method to send Forgot Password mail to users
	 *Steps:
	 *1. Get user by Email
	 *2. Update user with Pwd token , token expiry
	 *3. Send mail
	 *
	 */
	public Integer sendPasswordResetMail(String email) throws Exception{
		log.info("inside sendPasswordResetMail for user "+email);
		TimecardUser timeCardUser=this.userDao.getTimecardUserByEmail(email);
		String passwordToken=this.generateAccountToken();
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 1);
		Date validity=cal.getTime();
		timeCardUser.setTokenExpiryDate(validity);
		timeCardUser.setPasswordToken(passwordToken);
		Integer result=this.userDao.saveOrUpdateTimecardUser(timeCardUser);
		TimecardUserDto timCardUserDto=TimecardUserDto.populateTimeCardUser(timeCardUser);	
		try{
			this.emailSender.sendForgotPasswordMail(timCardUserDto);
		}
		catch(MailNotSendException e){
			log.error("Failed to send email "+e.toString());
			throw new MailNotSendException();
		}		
		return result;
	}
	
	
	
	
	
	/**
	 * 
	 * Created by Jeevan on 17-Sep-2014 5:51:32 pm		
		@return
		Utility method to generate Account Token
	 */
	private String generateAccountToken(){
		log.info("inside generateAccountToken()");
		String uuid=UUID.randomUUID().toString();
		String token=uuid.toString().replaceAll("-", "").toUpperCase();
		return token;
	}
	
	/**
	 * Created By Bhagya on 14-oct-2014  1:23:15 pm
	 * @return
	 *  Method to get UserId by password token
	 */
	
	public Integer getTimecardUserCredentialsIdbyPasswordToken(String passwordToken)throws TimecardUserNotFoundException{
		log.info("inside getTimeCardUserIdByPasswordToken");
		TimeCardUserCredentials timecardUser=this.userDao.getTimecardUserCredentialsByPasswordToken(passwordToken);
		Integer  credentialsId=timecardUser.getId();
		return credentialsId;
	}
	/**
	 * Created By Bhagya on 14-oct-2014
	 * @param userCredentialId,Password
	 * @return
	 * 
	 * Method  To Handle the Reset Password
	 */
	public Integer handlePasswordReset(Integer userCredentialId,String password)throws Exception{
		TimecardUser timecardUser=this.userDao.getTimecardUserByCredentialId(userCredentialId);
		Date expiryDate=timecardUser.getTokenExpiryDate();
		if(expiryDate.after(new Date())){
			String encryPassword=passwordEncoder.encode(password);
			TimeCardUserCredentials timeCardCredentials=timecardUser.getTimeCardCredentials();
			timeCardCredentials.setPassword(encryPassword);
			this.userDao.saveOrUpdateTimecardCredentials(timeCardCredentials);
			timecardUser.setTimeCardCredentials(timeCardCredentials);
			timecardUser.setPasswordToken("");
			timecardUser.setAccountExpiryDate(null);
			Integer result=this.userDao.saveOrUpdateTimecardUser(timecardUser);
			return result;
		}
		else{
			throw new PasswordTokenExpiedException();
		}
	}

	/**
	 * Created By Bhagya on 21-oct-2014
	 * @param userCredentialId,Password
	 * @return
	 * 
	 * Method to perform Change Password Process
	 * 
	 * Steps:
	 * 1. Check if Old password is valid
	 * 2. if valid, update new password
	 *       else throw error
	 */
	public Integer performPasswordChange(ChangePasswordDto changePasswordDto)throws Exception{
		 log.info("inside performPasswordChange");
		 TimecardUser timeCardUser=this.userDao.getTimecardUserByCredentialId(changePasswordDto.getUserId());
		 TimeCardUserCredentials timecardcredentials=timeCardUser.getTimeCardCredentials();
		 String newPassword=passwordEncoder.encode(changePasswordDto.getNewPassword());
		 System.out.println("NEW "+newPassword);
		 System.out.println("Exisiting "+timecardcredentials.getPassword());
		 if(!passwordEncoder.matches(changePasswordDto.getOldPassword(), timecardcredentials.getPassword())){
			 System.out.println("MATCHED");
			 throw new PasswordNotMatchedException();
		 }
		 timecardcredentials.setPassword(newPassword);
		 Integer result=this.userDao.saveOrUpdateTimecardCredentials(timecardcredentials);
		 return result;
	 }
}
