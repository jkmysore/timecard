package org.kns.timecard.frontend.timecarduser.service;

import org.kns.timecard.exception.TimecardUserNotFoundException;
import org.kns.timecard.frontend.timecarduser.dto.TimecardUserDto;
import org.kns.timecard.frontend.timecarduser.dto.ChangePasswordDto;

/**
 * 
 * @author Jeevan -- KNS TECHNOLOGIES CORPORTATION
      Created on 17-Sep-2014 1:18:04 pm
      
    Interface for UserService   
 *
 */
public interface UserService {

	public TimecardUserDto getTimeCardUserByEmailorUsername(String username)throws TimecardUserNotFoundException;
	public TimecardUserDto getTimecardUserByEmail(String email)throws TimecardUserNotFoundException;
	public Integer sendPasswordResetMail(String email) throws Exception;
	public Integer getTimecardUserCredentialsIdbyPasswordToken(String passwordToken)throws TimecardUserNotFoundException;
	public Integer handlePasswordReset(Integer userId,String password)throws Exception;
	public Integer performPasswordChange(ChangePasswordDto changePasswordDto)throws Exception;
}
