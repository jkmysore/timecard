package org.kns.timecard.backend.organization.division.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.kns.timecard.backend.organization.division.exception.DivisionNotFoundException;
import org.kns.timecard.backend.organization.division.model.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Bhagya- KNS Technologies Corporation
 * 
 * Created By Bhagya On October 22nd,2014
 * DAO Implementation for DivisionDao
 *
 */
@Transactional
@Repository("divisionDao")
public class DivisionDaoImpl implements DivisionDao{
	
	
	private static Logger log=Logger.getLogger(DivisionDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Created By bhagya On October 22nd,2014
	 * @param division
	 * @return divisionId
	 * @throws Exception
	 * 
	 * Method to save or update Department Details
	 * 
	 */
	
	public Integer saveorUpdateDivisionDetails(Division division)throws Exception{
		log.info("inside saveorUpdateDivisionDetails");
		sessionFactory.getCurrentSession().saveOrUpdate(division);
		sessionFactory.getCurrentSession().flush();
		return division.getDivisionId();
	}
	
	/**
	 * Created By bhagya On October 22nd,2014
	 * @param page,pageSize,organizationId
	 * @return divisions
	 * @throws DivisionNotFoundException
	 * 
	 * Method to get All Division Details Based On Organization Id
	 * 
	 */
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Division> getAllDivisionsBasedOnOrganizationIdFromDB(Integer page,Integer pageSize,Integer organizationId) throws DivisionNotFoundException{
		log.info("inside getAllDivisionsFromDB()");
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Division.class)
				.add(Restrictions.eq("organization.organizationId", organizationId));
		
		if(null!=page && null!=pageSize){
			criteria.setMaxResults(pageSize);
			criteria.setFirstResult(page*pageSize);
		}
		criteria.addOrder(Order.desc("divisionId"));
		ArrayList<Division> divisions=(ArrayList<Division>) criteria.list();
		
		if(!divisions.isEmpty()){
			return divisions;
		}
		else{
			throw new DivisionNotFoundException();
		}
		
		
	}
	/**
	 * Created By Bhagya on October 22nd,2014
	 * @param 
	 * @return total count value of Divisions
	 * @throws Exception
	 * 
	 * Method For to Get Toatl Divisions Count Value
	 */
	
	public Integer getTotalDivisions() throws Exception{
		log.info("inside getTotalDivisions()");
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Division.class);
		criteria.setProjection(Projections.count("id")).list();
		Integer totalResults=Integer.valueOf (criteria.list().get(0).toString());
		return totalResults;
	}
	/**
	 * Created By Bhagya On October 22nd,2014
	 * @param divisionId
	 * @return division
	 * @throws DivisionNotFoundException
	 * 
	 * Method For Get The Division By DivsionId
	 */
	
	 @SuppressWarnings("unchecked")
		public Division getDivisionByDivisionId(Integer divisionId) throws DivisionNotFoundException{
			log.info("inside getDivisionByDivisionId()");
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Division.class)
			 									.add(Restrictions.eq("divisionId", divisionId));
			
			ArrayList<Division> divisions=( ArrayList<Division>)criteria.list();
			
			if(!divisions.isEmpty()){
				return divisions.get(0);
				
			}
			else{
				throw new DivisionNotFoundException();
			}
			
		}
}
