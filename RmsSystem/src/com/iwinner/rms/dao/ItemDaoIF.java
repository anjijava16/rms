package com.iwinner.rms.dao;

import java.util.List;

import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.model.ItemInfo;

public interface ItemDaoIF {
	public String addItem(ItemInfo itemInfo) throws DaoException;
	
	public Integer deleteItemId(Integer itemId) throws DaoException;

	public ItemInfo viewItemInfo(Integer itemId) throws DaoException;

	public List<ItemInfo> viewAllItems() throws DaoException;

	public Integer updateItem(ItemInfo itemInfo) throws DaoException;

	public List<Integer> getItemIds() throws DaoException;
}
