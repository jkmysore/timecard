package org.kns.timecard.backend.timecard.organization.dao;

import java.util.ArrayList;

import org.kns.timecard.backend.timecard.organization.exception.OrganizationNotFoundException;
import org.kns.timecard.backend.timecard.organization.model.Organization;
import org.kns.timecard.backend.timecard.organization.model.TimeCardPeriod;
import org.kns.timecard.backend.user.model.TimeCardUser;

/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 *	
 *	Created on June 27, 2014
 *	 Interface for Organization
 */
public interface OrganizationDao {

	public Integer saveorUpdateOrganization(Organization organization )throws Exception;
	public ArrayList<TimeCardPeriod> getTimecardPeriods() throws Exception;
	public TimeCardPeriod getTimeCardPeriodById(Integer id)throws Exception;
	public Organization getOrganizationByAdmin(TimeCardUser timeCardUser)throws OrganizationNotFoundException;
}
