package org.kns.timecard.backend.timecard.timecard.model;

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
import org.kns.timecard.backend.timecard.tasks.model.Tasks;


/**
 * 
 * @author JEEVAN
 * 
 * Created by Jeevan on June 18, 2014
 * Model Class to Preserve TimeCard
 *
 */

@Entity
@Table(name="kns_timecard_timecard")
public class TimeCard  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="timecard_id")
	private Integer timeCardId;	
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="organization_id")
	private Organization organization;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="project_id")
	private Project project;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="task_id")
	private Tasks tasks;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@Column(name="week_ending_date")
	private Date weekEndingDate;
	
	@Column(name="timecard_date")
	private Date timeCardDate;
	
	@Column(name="expense_type")
	private String expenseType;
	
	@Column(name="work_hours")
	private Double workHours;
	
	@Column(name="comments")
	private String comments;

	@Column(name="status")
	private Boolean status;
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getTimeCardId() {
		return timeCardId;
	}

	public void setTimeCardId(Integer timeCardId) {
		this.timeCardId = timeCardId;
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

	public Tasks getTasks() {
		return tasks;
	}

	public void setTasks(Tasks tasks) {
		this.tasks = tasks;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getWeekEndingDate() {
		return weekEndingDate;
	}

	public void setWeekEndingDate(Date weekEndingDate) {
		this.weekEndingDate = weekEndingDate;
	}

	public Date getTimeCardDate() {
		return timeCardDate;
	}

	public void setTimeCardDate(Date timeCardDate) {
		this.timeCardDate = timeCardDate;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	public Double getWorkHours() {
		return workHours;
	}

	public void setWorkHours(Double workHours) {
		this.workHours = workHours;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
