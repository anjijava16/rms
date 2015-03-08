package com.iwinner.rms.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TASK_INFO")
public class TaskInfo implements java.io.Serializable {

	@Id
	@Column(name = "TASK_ID")
	private Integer taskId;
	@Column(name = "TASK_NAME")
	private String taskName;
	@Column(name = "TASK_DATE")
	private Date taskDate;
	@Column(name = "TASK_COMMENTS")
	private String comments;
	@Column(name = "TASK_TASKUPDATETIME")
	private Timestamp taskUpdateTime;
	@Column(name = "TASK_UPDATEDBY")
	private String taskUpdatedBy;
	@Column(name = "TASK_PRIPORTY")
	private String taskPriporty;
	@Column(name = "TASK_TASKTIMESTAMP")
	private Timestamp taskTime;
	@Column(name = "TASK_IMPCOMMENTS")
	private String taskImpComments;
	@Column(name = "TASK_TYPE")
	private String taskType;

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Timestamp getTaskUpdateTime() {
		return taskUpdateTime;
	}

	public void setTaskUpdateTime(Timestamp taskUpdateTime) {
		this.taskUpdateTime = taskUpdateTime;
	}

	public String getTaskUpdatedBy() {
		return taskUpdatedBy;
	}

	public void setTaskUpdatedBy(String taskUpdatedBy) {
		this.taskUpdatedBy = taskUpdatedBy;
	}

	public String getTaskPriporty() {
		return taskPriporty;
	}

	public void setTaskPriporty(String taskPriporty) {
		this.taskPriporty = taskPriporty;
	}

	public Timestamp getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(Timestamp taskTime) {
		this.taskTime = taskTime;
	}

	public String getTaskImpComments() {
		return taskImpComments;
	}

	public void setTaskImpComments(String taskImpComments) {
		this.taskImpComments = taskImpComments;
	}

}
