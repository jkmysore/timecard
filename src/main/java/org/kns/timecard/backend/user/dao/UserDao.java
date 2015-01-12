package org.kns.timecard.backend.user.dao;

import java.util.ArrayList;

import javax.management.relation.RoleNotFoundException;

import org.kns.timecard.backend.user.exception.TimecardUserNotFoundException;
import org.kns.timecard.backend.user.model.Roles;
import org.kns.timecard.backend.user.model.TimeCardUser;

/** * 
 * @author JEEVAN
 * 
 * Created by Jeevan on June 19, 2014
 * Interface for UserDao..
 *
 */
public interface UserDao {

	public Integer saveOrUpdateTimecardUser(TimeCardUser timeCardUser)throws Exception;
	public void deleteUser(TimeCardUser timeCardUser)throws Exception;
	public TimeCardUser getUserByUsernameorEmail(String username)throws TimecardUserNotFoundException;
	public TimeCardUser getUserByEmail(String email)throws TimecardUserNotFoundException;
	public TimeCardUser getUserByUsername(String username)throws TimecardUserNotFoundException;
	public TimeCardUser getUserByUserId(Integer userId)throws TimecardUserNotFoundException;
	public ArrayList<TimeCardUser> getTimeCardUsers(Integer pageNo,Integer pageSize)throws TimecardUserNotFoundException;
	public Roles getRoleByRoleId(Integer roleId)throws RoleNotFoundException;
	public Roles getRoleByRoleName(String roleName) throws RoleNotFoundException;
	public ArrayList<Roles> getAllRoles()throws RoleNotFoundException;
	public Integer getTimecardUserIdByPasswordToken(String token)throws TimecardUserNotFoundException;
	
}
