package org.kns.timecard.backend.timecard.tasks.model;

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

import org.kns.timecard.backend.timecard.organization.model.Employee;
import org.kns.timecard.backend.timecard.organization.model.Organization;
import org.kns.timecard.backend.timecard.project.model.Project;

/*
 * Added By Jeevan on June 18,2014
 * Class to Handle Task Control for Users
 */

@Entity
@Table(name="kns_timecard_task_control")
public class TaskControl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="organization_id")
	private Organization organization;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="project_id")
	private Project project;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="task_id")
	private Tasks task;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="is_allowed")
	private Boolean isAllowed;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Tasks getTask() {
		return task;
	}

	public void setTask(Tasks task) {
		this.task = task;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getIsAllowed() {
		return isAllowed;
	}

	public void setIsAllowed(Boolean isAllowed) {
		this.isAllowed = isAllowed;
	}
	
	
	
}
