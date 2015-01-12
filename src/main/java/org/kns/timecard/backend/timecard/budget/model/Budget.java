package org.kns.timecard.backend.timecard.budget.model;
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
import org.kns.timecard.backend.timecard.project.model.Project;
import org.kns.timecard.backend.timecard.tasks.model.Tasks;
import org.kns.timecard.backend.user.model.TimeCardUser;

/*
 * Created by Jeevan on June 18, 2014
 * Class for Budget (Model).
 * 
 * Creates DB Budget
 */


@Table
@Entity(name="kns_timecard_budget")
public class Budget implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="budget_id")
	private Integer BudgetId;
	
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="organization_id")
	private Organization organization; 
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="project_id")
	private Project project;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="task_id")
	private Tasks task;
	
	@Column(name="is_current")
	private Boolean isCurrent;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="estimated_hours")
	private Double estimatedHours;
	
	@Column(name="estimated_cost")
	private Double estimatedCost;
	
	@Column(name="estimated_revenue")
	private Double estimatedRevenue;
	
	@Column(name="is_budget_exceed_allowed")
	private Boolean isBudgetExceedAllowed;
	
	@Column(name="is_original_change_order")
	private Boolean isOriginalChangeOrder;
	
	@ManyToOne
	@JoinColumn(name="created_by")
	private TimeCardUser createdBy;
	
	@ManyToOne
	@JoinColumn(name="modified_by")
	private TimeCardUser modifiedBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="modified_date")
	private Date modifiedDate;

	public Integer getBudgetId() {
		return BudgetId;
	}

	public void setBudgetId(Integer budgetId) {
		BudgetId = budgetId;
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

	public Double getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(Double estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public Double getEstimatedRevenue() {
		return estimatedRevenue;
	}

	public void setEstimatedRevenue(Double estimatedRevenue) {
		this.estimatedRevenue = estimatedRevenue;
	}

	public Boolean getIsBudgetExceedAllowed() {
		return isBudgetExceedAllowed;
	}

	public void setIsBudgetExceedAllowed(Boolean isBudgetExceedAllowed) {
		this.isBudgetExceedAllowed = isBudgetExceedAllowed;
	}

	public Boolean getIsOriginalChangeOrder() {
		return isOriginalChangeOrder;
	}

	public void setIsOriginalChangeOrder(Boolean isOriginalChangeOrder) {
		this.isOriginalChangeOrder = isOriginalChangeOrder;
	}

	public TimeCardUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(TimeCardUser createdBy) {
		this.createdBy = createdBy;
	}

	public TimeCardUser getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(TimeCardUser modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Boolean getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
	
	
	
	
	
	

}
