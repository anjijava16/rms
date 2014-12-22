package com.iwinner.rms.dao;

import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.model.Users;

public interface LoginDaoIF {
public boolean loginVerify()throws DaoException;
public Users getUserDetails()throws DaoException;
}
