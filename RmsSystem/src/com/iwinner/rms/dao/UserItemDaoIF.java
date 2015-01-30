package com.iwinner.rms.dao;

import java.util.List;

import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.model.ItemInfo;

public interface UserItemDaoIF {
	public String addUserItem(ItemInfo itemInfo,String username) throws DaoException;

	public Integer deleteUserItemId(Integer itemId,String username) throws DaoException;

	public ItemInfo viewUserItemInfo(Integer itemId,String username) throws DaoException;

	public List<ItemInfo> viewAllUserItems(String username) throws DaoException;

	public Integer updateUserItem(ItemInfo itemInfo,String username) throws DaoException;
}
