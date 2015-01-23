package org.kns.timecard.backend.admin.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.kns.timecard.backend.organization.organization.model.Organization;
import org.kns.timecard.backend.timecarduser.model.TimecardUser;
import org.kns.timecard.exception.OrganizationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created be Jeevan on Jan 12, 2015
 * DAO class for SUPER ADMIN
 * 
 * 
 * @author KNS-ACCONTS
 *
 */


@Transactional
@Repository("adminDao")
public class AdminDaoImpl implements AdminDao {

	
	private static Logger log=Logger.getLogger(AdminDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	/**
	 * Created By Bhagya On 14-oct-2014
	 * @param organization
	 * @return
	 * @throws 
	 * 
	 * Method For Saving Or Updating The Organization
	 */
	public Integer saveorUpdateOrganization(Organization organization )throws Exception{
		log.info("inside saveorUpdateOrganization");
		sessionFactory.getCurrentSession().saveOrUpdate(organization);
		sessionFactory.getCurrentSession().flush();
		log.info("Organization "+organization.getOrganizationName()+" created/updated Successfully");
		return organization.getOrganizationId();
	}
	
	
	
	
	
	
	 /**
	  * Created by Jeevan on Jan 21, 2014..
	  * Method to getAllOrganizations..
	  * 
	  * @param pageNo
	  * @param pageSize
	  * @param searchBy
	  * @param sortBy
	  * @param direction
	  * 
	  * @return
	  */
	@SuppressWarnings("unchecked")
	public ArrayList<Organization> getAllOrganizations(Integer pageNo,Integer pageSize, String searchBy,String sortBy,Boolean ascending)throws OrganizationNotFoundException{
		log.info("inside getAllOrganizations()");
		ArrayList<Organization> organizations=null;
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Organization.class);
		if(null!=searchBy){
			criteria.add(Restrictions.ilike("organizationName", "%"+searchBy+"%"));
		}			
		if(null!=sortBy){
			if(ascending){
				criteria.addOrder(Order.asc(sortBy));
			}
			else{
				criteria.addOrder(Order.desc(sortBy));
			}				
		}
		
		Integer totalOrganizations=criteria.list().size();
		
		if(null!=pageNo){
			criteria.setFirstResult(pageNo*pageSize);
			criteria.setMaxResults(pageSize);
		}					
		organizations=(ArrayList<Organization>) criteria.list();
		if(!organizations.isEmpty()){		
			organizations.get(0).setTotalOrganizations(totalOrganizations);
			return organizations;
		}
		else{
			throw new OrganizationNotFoundException();
		}			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Created on October 22nd, 2014 By bhagya
	 * @param timecarduser
	 * @return organizations
	 * @throws OrganizationNotFoundException
	 * 
	 * Method to get Organization by its admin and its managers
	 */
	
	@SuppressWarnings("unchecked")
	public ArrayList<Organization> getOrganizationsByAdminorManager(TimecardUser timeCardUser)throws OrganizationNotFoundException{
		log.info("inside getOrganizationByAdmin()");
		ArrayList<Organization> organizations=null;
		DetachedCriteria subquery = DetachedCriteria.forClass(Organization.class);
		subquery.createAlias("siteManagers", "sm");
		subquery.add(Restrictions.eq("sm.userId",timeCardUser.getUserId()));
		subquery.setProjection(Projections.property("id"));
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Organization.class);
		 criteria.add(
		            Restrictions.or(
		            		Restrictions.eq("siteAdmin", timeCardUser),
		                    Subqueries.propertyIn("siteManagers", subquery)
		                    )
		            );
		organizations=(ArrayList<Organization>) criteria.list();
		if(!organizations.isEmpty()){
			return organizations;
		}
		else{
			throw new OrganizationNotFoundException();
		}
		
	}
	
	
	
	
	
	
	/**
	 * Created By Bhagya On October 22nd,2014
	 * @param organizationId
	 * @return organization
	 * @throws organizationNotFoundException
	 * 
	 * Method to get the organization By ID
	 */
	
	 @SuppressWarnings("unchecked")
		public Organization getOrganizationById(Integer organizationId) throws OrganizationNotFoundException{
			 log.info("inside getOrganizationById()");
			 ArrayList<Organization> organizations=null;
				Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Organization.class);
				organizations=	(ArrayList<Organization>) criteria.add(Restrictions.eq("id", organizationId))
								.list();
				if(!organizations.isEmpty()){
					return organizations.get(0);
				}
				else{
					throw new OrganizationNotFoundException();
				}
				
		 }
	
	
}
