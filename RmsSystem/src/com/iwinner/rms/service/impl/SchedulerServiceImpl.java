package com.iwinner.rms.service.impl;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.dao.SchedulerServiceDaoIF;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.factory.DaoFactory;
import com.iwinner.rms.factory.ServiceFactory;
import com.iwinner.rms.service.SchedulerServiceIF;

public class SchedulerServiceImpl implements SchedulerServiceIF {
	private SchedulerServiceDaoIF schedulerServiceDaoIF;
	public Integer updateNoOfUsersIncative() throws ServiceException {
		Integer noOfUserInactive=RMSConstants.DEFAULT_INACTIVE;
		try {
			schedulerServiceDaoIF=DaoFactory.getSchedulerFactory();
			noOfUserInactive=schedulerServiceDaoIF.updateNoOfUsersIncative();
		} catch (DaoException e) {
		}
		return noOfUserInactive;
	}
	public Integer updatePasswordExpireDays() throws ServiceException {
		Integer passwordExpireDays=RMSConstants.NO_OF_EXPIRE_PROCESSING_DEFAULT;
		try {
			schedulerServiceDaoIF=DaoFactory.getSchedulerFactory();
			passwordExpireDays=schedulerServiceDaoIF.updatePasswordExpireDays();
		} catch (DaoException e) {
		}
		return passwordExpireDays;
	}
}
