package org.kns.timecard.backend.admin.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.kns.timecard.backend.organization.organization.model.Organization;
import org.kns.timecard.backend.timecarduser.model.TimecardUser;
import org.kns.timecard.exception.OrganizationNotFoundException;


/**
 * Created be Jeevan on Jan 12, 2015
 * DAO class for SUPER ADMIN
 * 
 * 
 * @author KNS-ACCONTS
 *
 */



public interface AdminDao {

	
	public Integer saveorUpdateOrganization(Organization organization )throws Exception;
	public ArrayList<Organization> getOrganizationsByAdminorManager(TimecardUser timeCardUser)throws OrganizationNotFoundException;
	public Organization getOrganizationById(Integer organizationId) throws OrganizationNotFoundException;
	public ArrayList<Organization> getAllOrganizations(Integer pageNo,Integer pageSize, String searchBy,String sortBy,Boolean ascending)throws OrganizationNotFoundException;
	
	
}
