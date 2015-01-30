package com.iwinner.rms.dao;

import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.model.Users;
/**
 * It is HomeInformationDaoIF interface for Home Page information here profile,about,help
 * @author anjaiah@iwinner.com
 *
 */
public interface HomeInformationDaoIF {
	
	public Users profieInformation(String username)throws DaoException;

	
}
