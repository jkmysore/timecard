package org.kns.timecard.backend.user.dao;

import java.util.ArrayList;

import javax.management.relation.RoleInfoNotFoundException;
import javax.management.relation.RoleNotFoundException;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.kns.timecard.backend.user.exception.TimecardUserNotFoundException;
import org.kns.timecard.backend.user.model.Roles;
import org.kns.timecard.backend.user.model.TimeCardUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * @author Jeevan
 *
 * Created on June 19, 2014
 * Dao for User 
 */
@Repository("userDao")
@Transactional(value="transactionManager")
public class UserDaoImpl implements UserDao{

	private static Logger log=Logger.getLogger(UserDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	/*
	 * Method to perform Save or Update  of TIme Card User
	 */
	public Integer saveOrUpdateTimecardUser(TimeCardUser timeCardUser)throws Exception{
		log.info("inside saveOrUpdateTimeCardUser()");
		sessionFactory.getCurrentSession().saveOrUpdate(timeCardUser);
		sessionFactory.getCurrentSession().flush();
		return timeCardUser.getUserId();
	}
	
	
	
	/*
	 * Created by Jeevan on June 19, 2014
	 * Method to Delete TimeCardUsers
	 */
	public void deleteUser(TimeCardUser timeCardUser)throws Exception{
		log.info("inside deleteUser()");
		sessionFactory.getCurrentSession().delete(timeCardUser);
		sessionFactory.getCurrentSession().flush();
	}
	
	
	
	
	/*
	 * Created by Jeevan on June 19, 2014,
	 * Method to get User by Email or UserName for Login..
	 * 
	 */
	@SuppressWarnings("unchecked")
	public TimeCardUser getUserByUsernameorEmail(String username)throws TimecardUserNotFoundException{
		log.info("inside getUserByUsernameorEmail()");
		ArrayList<TimeCardUser> timeCardUsers=(ArrayList<TimeCardUser>) sessionFactory.getCurrentSession().createCriteria(TimeCardUser.class)
				.add(Restrictions.or(Restrictions.eq("username", username).ignoreCase(), Restrictions.eq("email", username).ignoreCase()))
				.list();
		if(!timeCardUsers.isEmpty()){
			return timeCardUsers.get(0);
		}
		else{
			throw new TimecardUserNotFoundException();
		}
	}
	
	
	
	
	/*
	 * Created by Jeevan on June 19, 2014
	 * Method to get Useer by Email
	 */
	@SuppressWarnings("unchecked")
	public TimeCardUser getUserByEmail(String email)throws TimecardUserNotFoundException{
		log.info("inside getUserByUsernameorEmail()");
		ArrayList<TimeCardUser> timeCardUsers=(ArrayList<TimeCardUser>) sessionFactory.getCurrentSession().createCriteria(TimeCardUser.class)
				.add(Restrictions.eq("email", email).ignoreCase())
				.list();
		if(!timeCardUsers.isEmpty()){
			return timeCardUsers.get(0);
		}
		else{
			throw new TimecardUserNotFoundException();
		}
	}
	
	
	/*
	 * Created by Jeevan on June 19, 2014
	 * Method to get Useer by Email
	 */
	@SuppressWarnings("unchecked")
	public TimeCardUser getUserByUsername(String username)throws TimecardUserNotFoundException{
		log.info("inside getUserByUsernameorEmail()");
		ArrayList<TimeCardUser> timeCardUsers=(ArrayList<TimeCardUser>) sessionFactory.getCurrentSession().createCriteria(TimeCardUser.class)
				.add(Restrictions.eq("username", username).ignoreCase())
				.list();
		if(!timeCardUsers.isEmpty()){
			return timeCardUsers.get(0);
		}
		else{
			throw new TimecardUserNotFoundException();
		}
	}
	
	
	
	/*
	 * Created by Jeevan on June 19, 2014
	 * Method to get Useer by Email
	 */
	@SuppressWarnings("unchecked")
	public TimeCardUser getUserByUserId(Integer userId)throws TimecardUserNotFoundException{
		log.info("inside getUserByUsernameorEmail()");
		ArrayList<TimeCardUser> timeCardUsers=(ArrayList<TimeCardUser>) sessionFactory.getCurrentSession().createCriteria(TimeCardUser.class)
				.add(Restrictions.eq("userId", userId))
				.list();
		if(!timeCardUsers.isEmpty()){
			return timeCardUsers.get(0);
		}
		else{
			throw new TimecardUserNotFoundException();
		}
	}
	
	
	
	/*
	 * Created by Jeevan on June 19, 2014
	 * Method to get all Users
	 * developed to handle pagination too
	 * 
	 * Apply Pageno=null and pagezie=null if no pagintion required
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<TimeCardUser> getTimeCardUsers(Integer pageNo,Integer pageSize)throws TimecardUserNotFoundException{
		log.info("inside getTimeCardUsers()");
		ArrayList<TimeCardUser> timeCardUsers=null;
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(TimeCardUser.class);
		
		if(null!=pageNo){
			criteria.setFirstResult(pageNo*pageSize);
			criteria.setMaxResults(pageSize);
		}
		timeCardUsers=(ArrayList<TimeCardUser>) criteria.list();
		if(!timeCardUsers.isEmpty()){
			return timeCardUsers;
		}
		else{
			throw new TimecardUserNotFoundException();
		}
	}
	
	

	/*
	 * GCreated by Jeevan on June 19, 2014
	 * Method to get User Roles By ID.
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
	
	
	
	/*
	 * Created by Jeevan on June 30, 2014
	 * Method to get Role by Role Name
	 */
	@SuppressWarnings("unchecked")
	public Roles getRoleByRoleName(String roleName) throws RoleNotFoundException{
		log.info("inside getRoleByRoleName()");
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
	
	
	/*
	 * Created by Jeevan on June 19, 2014
	 * Method to get all Roles
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Roles> getAllRoles()throws RoleNotFoundException{
		log.info("inside getAllRoles()");
		ArrayList<Roles> roles=(ArrayList<Roles>) sessionFactory.getCurrentSession().createCriteria(Roles.class).list();
		if(!roles.isEmpty()){
			return roles;
		}
		else{
			throw new RoleNotFoundException();
		}
	}
	
	
	/*
	 * Created by Jeevan on June 25, 2014
	 * Method to get Timecard User by PasswordToken
	 */
	@SuppressWarnings("unchecked")
	public Integer getTimecardUserIdByPasswordToken(String token)throws TimecardUserNotFoundException{
		log.info("inside getTimeCardUserByPasswordToken()");
		ArrayList<Integer> users= (ArrayList<Integer>) sessionFactory.getCurrentSession().createCriteria(TimeCardUser.class)
				.add(Restrictions.eq("passwordToken", token))
				.setProjection(Projections.property("userId"))
				.list();
		if(!users.isEmpty()){
			return users.get(users.size()-1);
		}
		else{
			throw new TimecardUserNotFoundException();
		}
	}
	
	
	
	
}
