package org.kns.timecard.frontend.timecard.organization.dto;

import org.kns.timecard.backend.timecard.organization.model.TimeCardPeriod;

/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 *	on June 26, 2014
 */
public class TimecardPeriodDto {

		private Integer id=1;
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
		
		
		
		public static TimecardPeriodDto populateTimecardPeriodDto(TimeCardPeriod period){
			TimecardPeriodDto periodDto=new TimecardPeriodDto();
			periodDto.setId(period.getId());
			periodDto.setTimeCardPeriod(period.getTimeCardPeriod());
			return periodDto;
		}
		
		
		
}

