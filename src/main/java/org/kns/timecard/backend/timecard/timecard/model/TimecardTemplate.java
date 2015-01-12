package org.kns.timecard.backend.timecard.timecard.model;

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

/**
 * 
 * @author JEEVAN
 * 
 * Created by Jeevan on June 18, 2014
 * Model for TImeCard Template 
 *
 */
@Entity
@Table(name="kns_timecard_template")
public class TimecardTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue
	@Column(name="template_id")
	private Integer templateId;

	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="timecard_id")
	private TimeCard timeCard;

	@Column(name="template_name")
	private String templateName;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="employee_id")
	private Employee employee;

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public TimeCard getTimeCard() {
		return timeCard;
	}

	public void setTimeCard(TimeCard timeCard) {
		this.timeCard = timeCard;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	


}
