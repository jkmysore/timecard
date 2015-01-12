package org.kns.timecard.backend.vacations.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kns.timecard.backend.timecard.organization.model.Employee;
import org.kns.timecard.backend.timecard.organization.model.Organization;

@Entity
@Table(name="kns_timecard_eligible_vacations")
public class EligibleVacations implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer vacationId;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="organization_id")
	private Organization organization;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@Column(name="available_vacations")
	private Double availableVacations;
	
	@Column(name="vacataions_used")
	private Double vacationsUsed;
	
	@Column(name="available_optional_hoidays")
	private Integer availableOptionalHolidays;
	
	@Column(name="optional_holidays_used")
	private Integer optionalHolidaysUsed;
	
	@Column(name="year")
	private Integer year;
	

}
