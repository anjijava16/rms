package com.iwinner.rms.service;

import java.util.List;

import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.model.ItemInfo;

public interface ItemServiceIF {
	
	public String addItem(ItemInfo itemInfo) throws ServiceException;
	public Integer deleteItemId(Integer itemId) throws ServiceException;
	public ItemInfo viewItemInfo(Integer itemId) throws ServiceException;

	public List<ItemInfo> viewAllItems() throws ServiceException;

	public Integer updateItem(ItemInfo itemInfo) throws ServiceException;

	public String addUserItem(ItemInfo itemInfo, String username)
			throws ServiceException;

	public Integer deleteUserItemId(Integer itemId, String username)
			throws ServiceException;

	public ItemInfo viewUserItemInfo(Integer itemId, String username)
			throws ServiceException;

	public List<ItemInfo> viewAllUserItems(String username)
			throws ServiceException;

	public Integer updateUserItem(ItemInfo itemInfo, String username)
			throws ServiceException;
}
