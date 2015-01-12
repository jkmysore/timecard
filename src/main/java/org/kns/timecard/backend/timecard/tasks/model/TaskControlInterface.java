package org.kns.timecard.backend.timecard.tasks.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * Created by Jeevan on June 18, 2014
 * Class to handle Excel load of Task Controls
 *
 */
@Entity
@Table(name="kns_timecard_task_control_interface")
public class TaskControlInterface implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;

	@Column(name="project_id")
	private Integer projectId;

	@Column(name="project_number")
	private String projectNumber;


	@Column(name="task_id")
	private Integer taskId;
	
	@Column(name="task_number")
	private String taskNumber;

	@Column(name="employee_id")
	private Integer employeeId;

	@Column(name="employee_number")
	private String employeeNumber;

	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="is_allowed")
	private Boolean isAllowed;
	
	


	

}
