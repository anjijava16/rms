package com.iwinner.rms.service;

import java.util.List;

import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.model.ItemInfo;

public interface UserItemServiceIF {
	public List<ItemInfo> viewAllUserItems(String username) throws ServiceException; 
}
