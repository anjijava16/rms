package com.iwinner.rms.dao;

import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.model.Users;

public interface LoginDaoIF {
public boolean loginVerify(String username,String password)throws DaoException;
public Users getUserDetails(String username)throws DaoException;
public String forgotPassword(String username,String password)throws DaoException;

}
