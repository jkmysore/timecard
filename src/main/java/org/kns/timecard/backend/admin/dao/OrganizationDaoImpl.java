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
import org.kns.timecard.exception.*;
import org.kns.timecard.backend.organization.organization.model.Organization;
import org.kns.timecard.backend.organization.organization.model.OrganizationConfig;
import org.kns.timecard.backend.organization.organization.model.TimeCardPeriod;
import org.kns.timecard.backend.timecarduser.model.TimecardUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * Created By Bhagya on october 14, 2014
 *	 Service For Organization Dao
 */

@Transactional
@Repository("organizationDao")
public class OrganizationDaoImpl implements OrganizationDao {
	
		private static Logger log=Logger.getLogger(OrganizationDaoImpl.class);
		
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
			
		
		/**
		 * Created on October 21st, 2014 By bhagya
		 * @param 
		 * @return timecardPeriods
		 * @throws 
		 * 
		 * Method to get All TimeCard Periods
		 */
		
		@SuppressWarnings("unchecked")
		public ArrayList<TimeCardPeriod> getTimecardPeriods() throws Exception{
			log.info("inside getTimecardPeriods()");
			ArrayList<TimeCardPeriod> timecardPeriods=(ArrayList<TimeCardPeriod>) sessionFactory.getCurrentSession().createCriteria(TimeCardPeriod.class)
					.list();
			if(!timecardPeriods.isEmpty()){
				return timecardPeriods;
			}
			else{
				throw new Exception();
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
		 * Created By Bhagya On Feb 26th,2015
		 * @param userId
		 * @return organization
		 * @throws organizationNotFoundException
		 * 
		 * Method to get the organization By UserID
		 */
		
		 @SuppressWarnings("unchecked")
			public Organization getOrganizationByUserId(Integer userId) throws OrganizationNotFoundException{
				 log.info("inside getOrganizationByUserId()");
				 ArrayList<Organization> organizations=null;
					Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Organization.class);
					organizations=	(ArrayList<Organization>) criteria.add(Restrictions.eq("siteAdmin.userId", userId))
									.list();
					if(!organizations.isEmpty()){
						return organizations.get(0);
					}
					else{
						throw new OrganizationNotFoundException();
					}
					
		  }
	 
	 
		
		/**
		 * Created By Bhagya On Feb 26th,2015
		 * @param organizationConfiguration
		 * @return
		 * @throws 
		 * 
		 * Method For Saving Or Updating The Organization Configuartion
		 */
		public Integer saveorUpdateOrganizationConfiguration(OrganizationConfig orgConfiguration )throws Exception{
			log.info("inside saveorUpdateOrganizationConfiguration");
			sessionFactory.getCurrentSession().saveOrUpdate(orgConfiguration);
			sessionFactory.getCurrentSession().flush();
			return orgConfiguration.getConfigId();
		}
		 /**
		 * Created By Bhagya On Feb 27th,2015
		 * @param userId
		 * @return organization
		 * @throws organizationNotFoundException
		 * 
		 * Method to get the organization By its Admin and Managers
		 */
		
		 @SuppressWarnings("unchecked")
			public Organization getOrganizationByUserEmail(TimecardUser timeCardUser) throws OrganizationNotFoundException{
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
					return organizations.get(0);
				}
				else{
					throw new OrganizationNotFoundException();
				}
					
		  }
}
