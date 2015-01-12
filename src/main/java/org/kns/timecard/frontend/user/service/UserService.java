package org.kns.timecard.frontend.user.service;

import org.kns.timecard.backend.user.exception.TimecardUserNotFoundException;
import org.kns.timecard.frontend.user.dto.ChangePasswordDto;
import org.kns.timecard.frontend.user.dto.TimecardUserDto;

public interface UserService {

	public TimecardUserDto getTimeCardUserByUsernameorEmail(String username)throws TimecardUserNotFoundException;
	public TimecardUserDto getTimeCardUserByEmail(String email) throws TimecardUserNotFoundException;
	public TimecardUserDto getTimecardUserbyUsername(String username)throws TimecardUserNotFoundException;
	public Integer sendPasswordResetMail(String email) throws Exception;
	public Integer getTimecardUserIdbyPasswordToken(String passwordToken)throws TimecardUserNotFoundException;
	public Integer handlePasswordReset(Integer userId,String password)throws Exception;
	public TimecardUserDto getTimecardUserbyUserId(Integer userId)throws TimecardUserNotFoundException;
	public Integer performPasswordChange(ChangePasswordDto changePasswordDto)throws Exception;
	public Integer handleFirstTimeVisitofUser(Integer userId)throws Exception;
	
	
}

