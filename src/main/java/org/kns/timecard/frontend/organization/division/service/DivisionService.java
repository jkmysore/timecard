package org.kns.timecard.frontend.organization.division.service;

import java.util.ArrayList;

import org.kns.timecard.exception.DivisionNotFoundException;
import org.kns.timecard.frontend.organization.division.dto.DivisionDto;
/**
 * 
 * @author Bhagya -KNS Technology Corporation
 *
 *CReated On October 22nd ,2014
 *Interface for DivisionService
 */
public interface DivisionService{
	public Integer savingTheAddedorUpdatedDivisionDetails(DivisionDto divisionDto,Integer organizationId) throws Exception;
	public ArrayList<DivisionDto> getAllDivisionsBasedOnOrganizationId(Integer page,Integer pageSize,Integer organizationId) throws DivisionNotFoundException;
	public Integer getDivisionTotalResults() throws Exception;
	public DivisionDto getDivisionDetailsByDivisionId(Integer divisionId) throws DivisionNotFoundException;
}