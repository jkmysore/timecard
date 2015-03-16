package org.kns.timecard.backend.timecarduser.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.kns.timecard.exception.RoleNotFoundException;
import org.kns.timecard.exception.TimecardUserNotFoundException;
import org.kns.timecard.backend.timecarduser.model.Roles;
import org.kns.timecard.backend.timecarduser.model.TimeCardUserCredentials;
import org.kns.timecard.backend.timecarduser.model.TimecardUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * @author JEEVAN
 * Created by Jeevan on September 10, 2014
 * Dao for User *
 */

@Transactional(value="transactionManager")
@Repository("userDao")
public class UserDaoImpl implements UserDao {

	
	private static Logger log=Logger.getLogger(UserDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Basic Methods should include
	 * 
	 * Saving Timecard User
	 * Saving TmecardUser Credentials
	 * Delete both
	 * Get All
	 * Get By Id
	 * Get by Username
	 * Get by Email
	 * Paginations etc
	 * 
	 * 
	 */
	
	
	/**
	 * Created by Jeevan on Septmber 10, 2014
	 * Method to save Time card User credentials
	 * @param credentials
	 * @return
	 * @throws Exception
	 */
	public Integer saveOrUpdateTimecardCredentials(TimeCardUserCredentials credentials)throws Exception{
		log.info("inside saveOrUpdateTimecardCredentials()");
		sessionFactory.getCurrentSession().saveOrUpdate(credentials);
		sessionFactory.getCurrentSession().flush();
		return credentials.getId();
	}
	
	
	/**
	 * 
	 * @param credentials
	 * Created by Jeevan on September 10, 2014
	 * Method to delete Timecard Credentials
	 */
	public void deleteTimecardCredentials(TimeCardUserCredentials credentials){
		log.info("inside deleteTimecardCredentials()");
		sessionFactory.getCurrentSession().delete(credentials);
		sessionFactory.getCurrentSession().flush();
	}
	
	/**
	 * Created by Jeevan on September 10, 2014
	 * 
	 * @param username
	 * @return
	 * @throws TimecardUserNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public TimecardUser getTimecardCredentialsForLogin(String username)throws TimecardUserNotFoundException{
		log.info("inside getTimecardCredentialsForLogin()");
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(TimecardUser.class);
		criteria.createCriteria("timeCardCredentials").add(Restrictions.or(Restrictions.eq("username", username), Restrictions.eq("email", username)));
		ArrayList<TimecardUser> timecardUsers=	(ArrayList<TimecardUser>) criteria.list();
		if(!timecardUsers.isEmpty()){
			return timecardUsers.get(0);
		}
		else{
			throw new TimecardUserNotFoundException();
		}
	}
	
	
	/**
	 * Created by Jeevan on September 10, 2014
	 * Method to save or update roles
	 * @param roles
	 * @return
	 * @throws Exception
	 */
	public Integer saveOrUpdateRoles(Roles roles)throws Exception{
		log.info("inside saveOrUpdateRoles()");
		sessionFactory.getCurrentSession().saveOrUpdate(roles);
		sessionFactory.getCurrentSession().flush();
		return roles.getRoleId();
	}
	
	
	/**
	 * Created by Jeevan on September 10, 2014
	 * method to delete Roles
	 * @param roles
	 * @throws Exception
	 */
	public void deleteRoles(Roles roles)throws Exception{
		log.info("inside deleteRoles");
		sessionFactory.getCurrentSession().delete(roles);
		sessionFactory.getCurrentSession().flush();
	}
	
	
	
	/**
	 * Created by Jeevan on September 10, 2014
	 * @param roleId
	 * @return
	 * @throws RoleNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public Roles getRoleByRoleId(Integer roleId)throws RoleNotFoundException{
		log.info("inside getRoleByRoleId()");
		ArrayList<Roles> roles=(ArrayList<Roles>) sessionFactory.getCurrentSession().createCriteria(Roles.class)
				.add(Restrictions.eq("roleId", roleId))
				.list();
		if(!roles.isEmpty()){
			return roles.get(0);
		}
		else{
			throw new RoleNotFoundException();
		}
	}
	
	
	
	/**
	 * Created by Jeevan on September 10, 2014
	 * Method to getRoleByRoleName
	 * @param roleName
	 * @return
	 * @throws RoleNotFoundException
	 */
	
	@SuppressWarnings("unchecked")
	public Roles getRoleByRoleName(String roleName)throws RoleNotFoundException{
		log.info("inside getRoleByRoleId()");
		ArrayList<Roles> roles=(ArrayList<Roles>) sessionFactory.getCurrentSession().createCriteria(Roles.class)
				.add(Restrictions.eq("roleName", roleName))
				.list();
		if(!roles.isEmpty()){
			return roles.get(0);
		}
		else{
			throw new RoleNotFoundException();
		}
	}
	
	
	/**
	 * Created by Jeevan on September 10, 2014
	 * Method to get all Roles
	 * @return
	 * @throws RoleNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Roles> getRoles() throws RoleNotFoundException{
		log.info("inside getRoles()");
		ArrayList<Roles> roles=(ArrayList<Roles>) sessionFactory.getCurrentSession().createCriteria(Roles.class)
				.list();
		if(!roles.isEmpty()){
			return roles;
		}
		else{
			throw new RoleNotFoundException();
		}
	}
	
	
	/**
	 * Created by Jeevan on September 10, 2014
	 * Method to saveOrUpdateTImecatrdUser()
	 * @param timecardUser
	 * @return
	 * @throws TimecardUserNotFoundException
	 */	
	public Integer saveOrUpdateTimecardUser(TimecardUser timecardUser)throws TimecardUserNotFoundException{
		log.info("inside saveorUpdateTimecardUser()");
		System.out.println("timecard user details at dao"+timecardUser.getFirstName()+"email" +timecardUser.getTimeCardCredentials().getEmail());
		sessionFactory.getCurrentSession().saveOrUpdate(timecardUser);
		sessionFactory.getCurrentSession().flush();
		return timecardUser.getUserId();
	}
	
	
	/**
	 * Created by Jeevan on Septemeber 10, 2014
	 * Method to delete TimecardUser
	 * @param timecardUser
	 * @throws Exception
	 */
	public void deleteTimecardUser(TimecardUser timecardUser)throws Exception{
		log.info("inside deleteTimecardUser()");
		sessionFactory.getCurrentSession().delete(timecardUser);
		sessionFactory.getCurrentSession().flush();
	}
	
	
	
	
	/**
	 * Created by Jeevan on September 10, 2014
	 * Method to get all Timecard sUsers
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws TimecardUserNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<TimecardUser> getAllTimecardUsers(Integer pageNo,Integer pageSize)throws TimecardUserNotFoundException{
		log.info("inside getAllTimecardUsers()");
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(TimecardUser.class);
		if(null!=pageSize){
			criteria.setFirstResult(pageNo*pageSize);
			criteria.setMaxResults(pageSize);
		}
		ArrayList<TimecardUser> timecardUsers=(ArrayList<TimecardUser>) criteria.list();
		if(!timecardUsers.isEmpty()){
			return timecardUsers;
		}
		else{
			throw new TimecardUserNotFoundException();
		}
	}
	
	
	
	/**
	 * Created by Jeevan on Seo
	 * @param userId
	 * @return
	 * @throws TimecardUserNotFoundException
	 */	
	@SuppressWarnings("unchecked")
	public TimecardUser getTimecardUserById(Integer userId)throws TimecardUserNotFoundException{
		log.info("inside getTimecardUserById()");
		ArrayList<TimecardUser> timecardUsers=(ArrayList<TimecardUser>) sessionFactory.getCurrentSession().createCriteria(TimecardUser.class)
				.add(Restrictions.eq("userId", userId))
				.list();
		if(!timecardUsers.isEmpty()){
			return timecardUsers.get(0);
		}
		else{
			throw new TimecardUserNotFoundException();
		}		
	}
	
	
	
	/**
	 * 
	 * Created by Jeevan on 17-Sep-2014 5:13:55 pm
		
		@param email
		@return
		@throws TimecardUserNotFoundException
		Method to get Time card user by Email
	 */
	@SuppressWarnings("unchecked")
	public TimecardUser getTimecardUserByEmail(String email)throws TimecardUserNotFoundException{
		log.info("inside getTimecardUserByEmail()");
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(TimecardUser.class);
		criteria.createCriteria("timeCardCredentials").add(Restrictions.eq("email", email));
		ArrayList<TimecardUser> timecardUsers=	(ArrayList<TimecardUser>) criteria.list();
		if(!timecardUsers.isEmpty()){
			return timecardUsers.get(0);
		}
		else{
			throw new TimecardUserNotFoundException();
		}
	}
	
	
	
	/**
	 * 
	 * Created by Jeevan on 17-Sep-2014 6:42:09 pm
		
		@param passwordToken
		@return
		@throws TimecardUserNotFoundException
	 *
	 *  Method to get User by Password Token
	 */
	/*@SuppressWarnings("unchecked")
	public TimecardUser getTimecardUserbyPasswordToken(String passwordToken)throws TimecardUserNotFoundException{
		log.info("inside getTimecardUserbyPasswordToken()");
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(TimecardUser.class);
		criteria.add(Restrictions.eq("passwordToken", passwordToken));
		ArrayList<TimecardUser> timecardUsers=(ArrayList<TimecardUser>) criteria.list();
		if(!timecardUsers.isEmpty()){
			return timecardUsers.get(0);
		}
		else{
			throw new TimecardUserNotFoundException();
		}
	}*/
	
	
	/**
	 * Craeted By Bhagya on 14-oct-2014 1:27:22 pm
	 * @param token
	 * @return 
	 * @throws TimeCardUserNotFoundException
	 * 
	 * Method to get Timecard User by PasswordToken
	 * 
	 */
	@SuppressWarnings("unchecked")
	public TimeCardUserCredentials getTimecardUserCredentialsByPasswordToken(String token)throws TimecardUserNotFoundException{
		log.info("inside getTimeCardUserByPasswordToken()");
		ArrayList<TimeCardUserCredentials> timeCardCredentials= (ArrayList<TimeCardUserCredentials>) sessionFactory.getCurrentSession().createCriteria(TimecardUser.class)
				.add(Restrictions.eq("passwordToken", token))
				.setProjection(Projections.property("timeCardCredentials"))
				.list();
		if(!timeCardCredentials.isEmpty()){
			return timeCardCredentials.get(0);
		}
		else{
			throw new TimecardUserNotFoundException();
		}
	}
	
	/**
	 * Craeted By Bhagya on 14-oct-2014 1:39:20 pm
	 * @param userId
	 * @return 
	 * @throws TimeCardUserNotFoundException
	 * 
	 * Method to get User By Userid
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	public TimecardUser getUserByUserId(Integer userId)throws TimecardUserNotFoundException{
		log.info("inside getUserByUserId");
		ArrayList<TimecardUser> timeCardUsers=(ArrayList<TimecardUser>) sessionFactory.getCurrentSession().createCriteria(TimecardUser.class)
				.add(Restrictions.eq("userId", userId))
				.list();
		if(!timeCardUsers.isEmpty()){
			return timeCardUsers.get(0);
		}
		else{
			throw new TimecardUserNotFoundException();
		}
	}
	
	
	
	
	/**
	 * Created By Bhagya on 14-oct-2014
	 * @param userCredentialId
	 * @return
	 * @throws TimecardUserNotFoundException 
	 * @throws
	 * 
	 * Method To get The TimeCard User Based On timeCredentialsId
	 */
	@SuppressWarnings("unchecked")
	public TimecardUser getTimecardUserByCredentialId(Integer userCredentialId) throws TimecardUserNotFoundException{
		log.info("inside getTimecardUserByCredentialId()");
		
		ArrayList<TimecardUser> timeCardUsers=(ArrayList<TimecardUser>) sessionFactory.getCurrentSession().createCriteria(TimecardUser.class)
				.add(Restrictions.eq("timeCardCredentials.id", userCredentialId))
				.list();
		if(!timeCardUsers.isEmpty()){
			return timeCardUsers.get(0);
		}
		else{
			throw new TimecardUserNotFoundException();
		}
		
	}
	
	
	
	
}
