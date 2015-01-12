package org.kns.timecard.frontend.organization.division.dto;

import org.kns.timecard.backend.organization.division.model.Division;
import org.kns.timecard.frontend.organization.organization.dto.OrganizationDto;

/**
 * Created by Bhagya on September 10, 2014
 * Dto for Division
 */

public class DivisionDto{
	private Integer divisionId;
	private String divisionNo;
	private OrganizationDto organization;
	private String divisionName;
	
	public Integer getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}
	public String getDivisionNo() {
		return divisionNo;
	}
	public void setDivisionNo(String divisionNo) {
		this.divisionNo = divisionNo;
	}
	public OrganizationDto getOrganization() {
		return organization;
	}
	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	
	/*
	 * Added By Bhagya on September 10,2014
	 * Method To follow Division dto
	 */
	public static DivisionDto  populateDivisionDto(Division division){
		DivisionDto divisionDto=new DivisionDto();
		divisionDto.setDivisionId(division.getDivisionId());
		divisionDto.setDivisionName(division.getDivisionName());
		divisionDto.setDivisionNo(division.getDivisionNo());
		divisionDto.setOrganization(OrganizationDto.populateOrganizationDto(division.getOrganization()));
		
		
		return divisionDto;
	}
}