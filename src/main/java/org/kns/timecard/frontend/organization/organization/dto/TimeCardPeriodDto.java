package org.kns.timecard.frontend.organization.organization.dto;

import org.kns.timecard.backend.organization.organization.model.TimeCardPeriod;

/**  
 * Created by Bhagya on September 10, 2014
 * Dto for TimeCardPeriod *
 */

public class TimeCardPeriodDto{
	
	private Integer id;
	private String timeCardPeriod;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTimeCardPeriod() {
		return timeCardPeriod;
	}
	public void setTimeCardPeriod(String timeCardPeriod) {
		this.timeCardPeriod = timeCardPeriod;
	}
	
	/*
	 * Added by bhagya on september 10,2014
	 * Method to follow timecard period Dto
	 */
	public static TimeCardPeriodDto populateTimecardPeriodDto(TimeCardPeriod period){
		TimeCardPeriodDto periodDto=new TimeCardPeriodDto();
		periodDto.setId(period.getId());
		periodDto.setTimeCardPeriod(period.getTimeCardPeriod());
		return periodDto;
	}
	
}