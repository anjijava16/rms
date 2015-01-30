package com.iwinner.rms.service;

import com.iwinner.rms.expections.ServiceException;

public interface SchedulerServiceIF {
	public Integer updateNoOfUsersIncative()throws ServiceException;
	public Integer updatePasswordExpireDays()throws ServiceException;
}
