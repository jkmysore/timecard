package org.kns.timecard.backend.organization.division.dao;

import java.util.ArrayList;

import org.kns.timecard.backend.organization.division.model.Division;
import org.kns.timecard.exception.DivisionNotFoundException;
import org.kns.timecard.exception.DivisionNotFoundFilterException;


/**
 * 
 * @author Bhagya- KNS Technologies Corporation
 * 
 * Created By Bhagya On October 22nd,2014
 * Interface for DivisionDao
 *
 */

public interface DivisionDao{
	
public Integer saveorUpdateDivisionDetails(Division division)throws Exception;
//public ArrayList<Division> getAllDivisionsBasedOnOrganizationIdFromDB(Integer page,Integer pageSize,Integer organizationId) throws DivisionNotFoundException;
public Integer getTotalDivisions() throws Exception;
public Division getDivisionByDivisionId(Integer divisionId) throws DivisionNotFoundException;
public ArrayList<Division> getDivisionsBasedOnOrganizationId(Integer organizationId,Integer pageNo,Integer pageSize,String sortBy,String searchBy,Boolean ascending) throws DivisionNotFoundException, DivisionNotFoundFilterException;

}