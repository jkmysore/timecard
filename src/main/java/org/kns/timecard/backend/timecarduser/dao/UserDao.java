package org.kns.timecard.backend.timecarduser.dao;

import java.util.ArrayList;

import org.kns.timecard.exception.RoleNotFoundException;
import org.kns.timecard.exception.TimecardUserNotFoundException;
import org.kns.timecard.backend.timecarduser.model.Roles;
import org.kns.timecard.backend.timecarduser.model.TimeCardUserCredentials;
import org.kns.timecard.backend.timecarduser.model.TimecardUser;

public interface UserDao {
	public Integer saveOrUpdateTimecardCredentials(TimeCardUserCredentials credentials)throws Exception;
	public void deleteTimecardCredentials(TimeCardUserCredentials credentials);
	public TimecardUser getTimecardCredentialsForLogin(String username)throws TimecardUserNotFoundException;
	public Integer saveOrUpdateRoles(Roles roles)throws Exception;
	public void deleteRoles(Roles roles)throws Exception;
	public Roles getRoleByRoleId(Integer roleId)throws RoleNotFoundException;
	public Roles getRoleByRoleName(String roleName)throws RoleNotFoundException;
	public ArrayList<Roles> getRoles() throws RoleNotFoundException;
	public Integer saveOrUpdateTimecardUser(TimecardUser timecardUser)throws TimecardUserNotFoundException;
	public void deleteTimecardUser(TimecardUser timecardUser)throws Exception;
	public ArrayList<TimecardUser> getAllTimecardUsers(Integer pageNo,Integer pageSize)throws TimecardUserNotFoundException;
	public TimecardUser getTimecardUserById(Integer userId)throws TimecardUserNotFoundException;
	public TimecardUser getTimecardUserByEmail(String email)throws TimecardUserNotFoundException;
	public TimeCardUserCredentials getTimecardUserCredentialsByPasswordToken(String token)throws TimecardUserNotFoundException;
	public TimecardUser getUserByUserId(Integer userId)throws TimecardUserNotFoundException;
	public TimecardUser getTimecardUserByCredentialId(Integer userCredentialId) throws TimecardUserNotFoundException;
	
}
