package org.kns.timecard.frontend.timecard.organization.dto;
/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 *
 *	Created on July 03, 2014
 *	DTO for OrganizationConfiguraitons
 *  Not a simulation of Model
 *  main intention behind creation is to handle validations
 */
public class OrganizationConfigDto {
	
	
	private Integer configId;
	
	private TimecardPeriodDto timecardPeriod;
	
	private Double minHoursPerWeek=8.0;
	
	
	private Double maxHoursPerWeek=40.0;
	
	private Integer weekEndingDay=1;
	
	private String whineList="7 days";

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public TimecardPeriodDto getTimecardPeriod() {
		return timecardPeriod;
	}

	public void setTimecardPeriod(TimecardPeriodDto timecardPeriod) {
		this.timecardPeriod = timecardPeriod;
	}

	public Double getMinHoursPerWeek() {
		return minHoursPerWeek;
	}

	public void setMinHoursPerWeek(Double minHoursPerWeek) {
		this.minHoursPerWeek = minHoursPerWeek;
	}

	public Double getMaxHoursPerWeek() {
		return maxHoursPerWeek;
	}

	public void setMaxHoursPerWeek(Double maxHoursPerWeek) {
		this.maxHoursPerWeek = maxHoursPerWeek;
	}

	public Integer getWeekEndingDay() {
		return weekEndingDay;
	}

	public void setWeekEndingDay(Integer weekEndingDay) {
		this.weekEndingDay = weekEndingDay;
	}

	public String getWhineList() {
		return whineList;
	}

	public void setWhineList(String whineList) {
		this.whineList = whineList;
	}
	
	
	
	
	
}
