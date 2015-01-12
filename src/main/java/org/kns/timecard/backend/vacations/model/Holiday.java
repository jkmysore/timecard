package org.kns.timecard.backend.vacations.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kns.timecard.backend.timecard.organization.model.Organization;


/*
 * Created by Jeevan on June 18, 2014
 * Model for Holiday
 * 
 */

@Entity
@Table(name="kns_timecard_holiday")
public class Holiday implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="holiday_id")
	private Integer holidayId;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="organization_id")
	private Organization organization;

	@Column(name="holiday_date")
	private Date holidayDate;
	
	@Column(name="holiday_name")
	private String holidayName;
	
	@Column(name="holiday_type")
	private String holidayType;
	
	@Column(name="year")
	private Integer year;

	public Integer getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(Integer holidayId) {
		this.holidayId = holidayId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Date getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public String getHolidayType() {
		return holidayType;
	}

	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	
	
}
