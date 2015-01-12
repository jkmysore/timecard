package org.kns.timecard.backend.timecard.organization.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.kns.timecard.backend.timecard.organization.exception.OrganizationNotFoundException;
import org.kns.timecard.backend.timecard.organization.model.Organization;
import org.kns.timecard.backend.timecard.organization.model.TimeCardPeriod;
import org.kns.timecard.backend.user.model.TimeCardUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 *	
 *Created on June 27, 2014
 *Service for Organization dAo
 */
@Transactional
@Repository("organizationDao")
public class OrganizationDaoImpl implements OrganizationDao {
	
		private static Logger log=Logger.getLogger(OrganizationDaoImpl.class);
		
		@Autowired
		private SessionFactory sessionFactory;
	
		
		/*
		 * Created on June 27, 2014
		 * Method to save or update Organization
		 */
		public Integer saveorUpdateOrganization(Organization organization )throws Exception{
			log.info("inside saveorUpdateOrganization");
			sessionFactory.getCurrentSession().saveOrUpdate(organization);
			sessionFactory.getCurrentSession().flush();
			log.info("Organization "+organization.getOrganizationName()+" created/updated Successfully");
			return organization.getId();
		}
		
		
		
		/*
		 * Added by Jeevan on July 03, 2014
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
		
	   /*
	    * Added by Jeevan on July 04, 2014
	    * Method to get  Timecard by Time card period id	
	    */
		@SuppressWarnings("unchecked")
		public TimeCardPeriod getTimeCardPeriodById(Integer id)throws Exception{
			log.info("inside getTimecardPeriodById()");
			ArrayList<TimeCardPeriod> periods=(ArrayList<TimeCardPeriod>) sessionFactory.getCurrentSession().createCriteria(TimeCardPeriod.class)
					.add(Restrictions.eq("id", id))
					.list();
			if(!periods.isEmpty()){
				return periods.get(0);
			}
			else{
				throw new Exception();
			}
		}
		
		
		/*
		 * Created by Jeevan on July 03, 2014
		 * Mwthod to Organizationby its admin
		 */
		@SuppressWarnings("unchecked")
		public Organization getOrganizationByAdmin(TimeCardUser timeCardUser)throws OrganizationNotFoundException{
			log.info("inside getOrganizationByAdmin()");
			ArrayList<Organization> organizations=null;
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Organization.class);
			organizations=	(ArrayList<Organization>) criteria.add(Restrictions.eq("siteAdmin", timeCardUser))
							.list();
			if(!organizations.isEmpty()){
				return organizations.get(0);
			}
			else{
				throw new OrganizationNotFoundException();
			}
			
		}
		
		
	
}
