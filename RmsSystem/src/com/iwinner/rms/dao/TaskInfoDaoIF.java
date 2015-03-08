package com.iwinner.rms.dao;

import java.util.Date;
import java.util.List;

import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.model.TaskInfo;

public interface TaskInfoDaoIF {
	public Integer addTasks(TaskInfo taskInfo) throws DaoException;

	public TaskInfo viewTaskInfo(Date isVerifyDate) throws DaoException;

	public List<TaskInfo> viewAllTasksInfo() throws DaoException;

	public List<TaskInfo> viewInfoFromToDates(Date fromDate, Date toDate)throws DaoException;

	public Integer deleteTask(Integer taskId) throws DaoException;

	public Integer updateTaskInfo(TaskInfo taskInfo) throws DaoException;

	public List<TaskInfo> getFeatureTasksInfoList() throws DaoException;
	public  Integer countOfTaskId()throws DaoException;

}
