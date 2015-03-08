package com.iwinner.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.iwinner.rms.dao.UserItemDaoIF;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.factory.DaoFactory;
import com.iwinner.rms.model.ItemInfo;
import com.iwinner.rms.service.UserItemServiceIF;

public class UserItemServiceImpl implements UserItemServiceIF{

	private static UserItemDaoIF userItrDaoIF=null;
	public List<ItemInfo> viewAllUserItems(String username)	throws ServiceException {
		userItrDaoIF=DaoFactory.getUserItemFactory();
		List<ItemInfo> listOfUserItemsInfo=new ArrayList<ItemInfo>();
		try {
			listOfUserItemsInfo=userItrDaoIF.viewAllUserItems(username);
		} catch (DaoException e) {
		}
		return listOfUserItemsInfo;
	}
}
