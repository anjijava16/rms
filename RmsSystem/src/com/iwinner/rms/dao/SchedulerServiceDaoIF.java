package com.iwinner.rms.dao;

import com.iwinner.rms.expections.DaoException;

public interface SchedulerServiceDaoIF {
	public Integer updateNoOfUsersIncative()throws DaoException;
	public Integer updatePasswordExpireDays()throws DaoException;
}
