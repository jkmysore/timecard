package org.kns.timecard.backend.timecard.organization.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Created by Jeevan on June 16, 2014
 * Model to save all Time Card Types
 */

@Entity
@Table(name="kns_timecard_timecard_period")
public class TimeCardPeriod  implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;

	@Column(name="timecard_period")
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
	
	
}
