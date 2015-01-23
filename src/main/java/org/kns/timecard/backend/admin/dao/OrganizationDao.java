package org.kns.timecard.backend.admin.dao;

import java.util.ArrayList;

import org.kns.timecard.backend.organization.organization.model.Organization;
import org.kns.timecard.backend.organization.organization.model.TimeCardPeriod;
import org.kns.timecard.backend.timecarduser.model.TimecardUser;
import org.kns.timecard.exception.OrganizationNotFoundException;


/**
 * 
 * Created By Bhagya on october 14, 2014
 *	Interface for Organization
 */
public interface OrganizationDao {

	public Integer saveorUpdateOrganization(Organization organization )throws Exception;
	public ArrayList<TimeCardPeriod> getTimecardPeriods() throws Exception;
	public ArrayList<Organization> getOrganizationsByAdminorManager(TimecardUser timeCardUser)throws OrganizationNotFoundException;
	public Organization getOrganizationById(Integer organizationId) throws OrganizationNotFoundException;
}
