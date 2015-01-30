package com.iwinner.rms.dao;

import java.util.List;

import com.iwinner.rms.expections.DaoException;

public interface HomePageDaoIF {
	public List<String> getUsersInformation()throws DaoException;
}
