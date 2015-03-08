package com.iwinner.rms.service.impl;

import org.apache.log4j.Logger;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.dao.TaskInfoDaoIF;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.factory.DaoFactory;
import com.iwinner.rms.service.TaskServiceIF;

public class TaskInfoServiceImpl implements TaskServiceIF {
	private TaskInfoDaoIF taskInfoDaoIF;
	public static Logger LOGGER = Logger.getLogger(TaskInfoServiceImpl.class);

	public Integer getTaskId() throws DaoException {
		LOGGER.info("***** start of getTaskId() is processing ***** ") ;
		Integer taskId=RMSConstants.TASK_ID_PROCESSING;
		try{
		taskInfoDaoIF=DaoFactory.getTaskInfoDao();
		taskId=taskInfoDaoIF.countOfTaskId();
		}catch(Exception e){
			   taskId=RMSConstants.TASK_ID_FAILED;
			   LOGGER.info("##### Error Inside  getTaskId() is processing ##### "+e.getMessage()) ;
		}
		LOGGER.info("***** end of getTaskId() is processing ***** ") ;
		return taskId;
	}

}
