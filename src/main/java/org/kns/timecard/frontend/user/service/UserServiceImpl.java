package org.kns.timecard.frontend.user.service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.kns.timecard.backend.user.dao.UserDao;
import org.kns.timecard.backend.user.exception.MailNotSendException;
import org.kns.timecard.backend.user.exception.PasswordNotMatchedException;
import org.kns.timecard.backend.user.exception.PasswordTokenExpiedException;
import org.kns.timecard.backend.user.exception.TimecardUserNotFoundException;
import org.kns.timecard.backend.user.model.TimeCardUser;
import org.kns.timecard.common.logging.LoggingFactory;
import org.kns.timecard.frontend.common.utility.EmailSender;
import org.kns.timecard.frontend.user.dto.ChangePasswordDto;
import org.kns.timecard.frontend.user.dto.TimecardUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 * 
 *  Created by Jeevan on June 19, 2014
 *  Service class for User Activities
 *
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
	
	/*
	 * Created by Jeevan on June 20, 2014
	 * Method to get TimecarduserbasedonUsername or email
	 */
	public TimecardUserDto getTimeCardUserByUsernameorEmail(String username)throws TimecardUserNotFoundException{
		log.info("inside getTimeCardUserByUsernameorEmail()");
		TimeCardUser timecardUser=this.userDao.getUserByUsernameorEmail(username);
		TimecardUserDto timecardUserDto=TimecardUserDto.populateTimeCardUser(timecardUser);
		return timecardUserDto;
	}
	
	
	/*
	 * Created by Jeevan on June 25, 2014
	 * Method to get User by User Email
	 * Used for Checking if User Email already exists
	 */
	public TimecardUserDto getTimeCardUserByEmail(String email) throws TimecardUserNotFoundException{
		log.info("inside getTimeCardUserByEmail");
		TimeCardUser timeCardUser=this.userDao.getUserByEmail(email);
		TimecardUserDto timeCardUserDto=TimecardUserDto.populateTimeCardUser(timeCardUser);
		return timeCardUserDto;
	}
	
	
	
	/*
	 * Created by Jeevan on June 30,2014
	 * Method to get User by Username
	 * Used forvalidating username
	 */
	public TimecardUserDto getTimecardUserbyUsername(String username)throws TimecardUserNotFoundException{
		log.info("inside getTimeCardUserbyUsername");
		TimeCardUser timeCardUser=this.userDao.getUserByUsername(username);
		TimecardUserDto timecardUserDto=TimecardUserDto.populateTimeCardUser(timeCardUser);
		return timecardUserDto;
	}
	
	
	
	/*
	 * Created by Jeevan on June 25, 2014
	 * Method to Handle Forger Password Process
	 * Steps:
	 * 1. Get user by Emailk
	 * 2. Set Pwd token, Expiry date
	 * 3. Save
	 * 4. Send link via mail
	 */
	public Integer sendPasswordResetMail(String email) throws Exception{
		log.info("inside sendPasswordResetMail for user "+email);
		TimeCardUser timeCardUser=this.userDao.getUserByEmail(email);
		String passwordToken=this.generateAccountToken();
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 1);
		Date validity=cal.getTime();
		timeCardUser.setAccountExpiryDate(validity);
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
	
	

	
	/*
	 * Added by Jeevan on June 25, 2014
	 * Method tto get UserIb by password token
	 */
	public Integer getTimecardUserIdbyPasswordToken(String passwordToken)throws TimecardUserNotFoundException{
		log.info("inside getTimeCardUserIdByPasswordToken");
		Integer userId=this.userDao.getTimecardUserIdByPasswordToken(passwordToken);
		return userId;
	}
	
	
	/*
	 *Added by Jeevan on June 25, 2014
	 *Method to hande password Reset process
	 * @return
	 */
	public Integer handlePasswordReset(Integer userId,String password)throws Exception{
		TimeCardUser timecardUser=this.userDao.getUserByUserId(userId);
		Date expiryDate=timecardUser.getAccountExpiryDate();
		if(expiryDate.after(new Date())){
			String encryPassword=passwordEncoder.encode(password);
			timecardUser.setPassword(encryPassword);
			timecardUser.setPasswordToken("");
			timecardUser.setAccountExpiryDate(null);
			Integer result=this.userDao.saveOrUpdateTimecardUser(timecardUser);
			return result;
		}
		else{
			throw new PasswordTokenExpiedException();
		}
	}
	
	
	/*
	 * Added by Jeevan on July 01, 2014
	 * Method to get User by UserId
	 */
	public TimecardUserDto getTimecardUserbyUserId(Integer userId)throws TimecardUserNotFoundException{
		log.info("inside getTimecardUserbyUserId()");
		TimeCardUser timecardUser=this.userDao.getUserByUserId(userId);
		TimecardUserDto timecardUserDto=TimecardUserDto.populateTimeCardUser(timecardUser);
		return timecardUserDto;
	}
	
	
	
	
	
	/*
	 * Created by Jeevan on July 02 , 2014 
	 * Method to perform Change Password Process
	 * 
	 * Steps:
	 * 1. Check if Old password is valid
	 * 2. if valid, update new password
	 *       else throw error
	 * 
	 * 
	 */
	 public Integer performPasswordChange(ChangePasswordDto changePasswordDto)throws Exception{
		 log.info("inside performPasswordChange");
		 TimeCardUser timeCardUser=this.userDao.getUserByUserId(changePasswordDto.getUserId());
		 String newPassword=passwordEncoder.encode(changePasswordDto.getNewPassword());
		 System.out.println("NEW "+newPassword);
		 System.out.println("Exisiting "+timeCardUser.getPassword());
		 if(!passwordEncoder.matches(changePasswordDto.getOldPassword(), timeCardUser.getPassword())){
			 System.out.println("MATCHED");
			 throw new PasswordNotMatchedException();
		 }
		 timeCardUser.setPassword(newPassword);
		 Integer result=this.userDao.saveOrUpdateTimecardUser(timeCardUser);
		 return result;
	 }
	
	
	
	/*
	 * Created by Jeevan on July 04, 2014
	 * Method to Update First Time User Login Value
	 * Get user by Id
	 *update first visit to false
	 *update user
	 * 
	 */
	public Integer handleFirstTimeVisitofUser(Integer userId)throws Exception{
		log.info("inside handleFirstTimeVisitofUser()");
		TimeCardUser timecardUser=this.userDao.getUserByUserId(userId);
		timecardUser.setIsFirstLogin(false);
		Integer updateResult=this.userDao.saveOrUpdateTimecardUser(timecardUser);
		return updateResult;
	}
	
	
	
	
	
	
	/*
	 * Generates Unique Token
	 * Utility Method
	 */
	private String generateAccountToken(){
		log.info("inside generateAccountToken()");
		String uuid=UUID.randomUUID().toString();
		String token=uuid.toString().replaceAll("-", "").toUpperCase();
		return token;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
