package com.iwinner.rms.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.dao.ItemDaoIF;
import com.iwinner.rms.dao.UserItemDaoIF;
import com.iwinner.rms.dao.impl.ItemDaoImpl;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.factory.DaoFactory;
import com.iwinner.rms.model.ItemInfo;
import com.iwinner.rms.service.ItemServiceIF;

public class ItemServiceImpl implements ItemServiceIF {

	private ItemDaoIF itemDaoIF=null;
	private UserItemDaoIF userItemDaoIF=null;
	
	public String addItem(ItemInfo itemInfo) throws ServiceException {
		String addItem = RMSConstants.ADD_ITEM_PROCESSING;
		itemDaoIF=DaoFactory.getItemFactory();
		ItemInfo itemAdding=new ItemInfo();
		try {
			itemAdding.setDate(itemInfo.getDate());
			itemAdding.setItemId(ItemDaoImpl.incrementItem()+RMSConstants.ADD_ONE);
			itemAdding.setItemName(itemInfo.getItemName());
			itemAdding.setItemTakenPerson(itemInfo.getItemTakenPerson());
			itemAdding.setPersonsWith(itemInfo.getPersonsWith());
			itemAdding.setPrice(itemInfo.getPrice());
			itemAdding.setComments(itemInfo.getComments());
			itemAdding.setPurchasePlace(itemInfo.getPurchasePlace());
			itemAdding.setTakenTime(new Timestamp(new Date().getTime()));
			itemAdding.setTakingPlace(itemInfo.getTakingPlace());
			itemAdding.setUpdatedBy(itemInfo.getUpdatedBy());
			itemAdding.setUsername(itemInfo.getUsername());
			itemAdding.setUpdatedTime(new Timestamp(new Date().getTime()));
			addItem=itemDaoIF.addItem(itemAdding);
		} catch (DaoException e) {
		}
		return addItem;

	}
	public Integer deleteItemId(Integer itemId) throws ServiceException {
		Integer deleteItem = RMSConstants.DELETE_ITAM_PROCESSING;
		itemDaoIF=DaoFactory.getItemFactory();
		try {
			deleteItem=itemDaoIF.deleteItemId(itemId);
		} catch (DaoException e) {
		}
		return deleteItem;
	}

	public ItemInfo viewItemInfo(Integer itemId) throws ServiceException {
		itemDaoIF=DaoFactory.getItemFactory();
		ItemInfo itemInfo=new ItemInfo();
		try {
			itemInfo=itemDaoIF.viewItemInfo(itemId);
		} catch (DaoException e) {
		}
		return itemInfo;
	}

	public List<ItemInfo> viewAllItems() throws ServiceException {
		itemDaoIF=DaoFactory.getItemFactory();
		List<ItemInfo> listOfItem=new ArrayList<ItemInfo>();
		try {
			listOfItem=itemDaoIF.viewAllItems();
		} catch (DaoException e) {
		}
		return listOfItem;
	}

	public Integer updateItem(ItemInfo itemInfo) throws ServiceException {
		Integer updateItem = RMSConstants.UPDATE_ITAM_PROCESSING;
		itemDaoIF=DaoFactory.getItemFactory();
		try {
			itemInfo.setDate(new Date());
          
			updateItem=itemDaoIF.updateItem(itemInfo);
		} catch (DaoException e) {
		}
		return updateItem;
	}

	public String addUserItem(ItemInfo itemInfo, String username)
			throws ServiceException {
		String addItem = RMSConstants.ADD_USER_ITEM_PROCESSING;
		userItemDaoIF=DaoFactory.getUserItemFactory();
		ItemInfo itemAdding=new ItemInfo();
		try {
			itemAdding.setDate(new Date());
			itemAdding.setItemId(ItemDaoImpl.incrementItem()+RMSConstants.ADD_ONE);
			itemAdding.setItemName(itemInfo.getItemName());
			itemAdding.setItemTakenPerson(itemInfo.getItemTakenPerson());
			itemAdding.setPersonsWith(itemInfo.getPersonsWith());
			itemAdding.setPrice(itemInfo.getPrice());
			itemAdding.setPurchasePlace(itemInfo.getPurchasePlace());
			itemAdding.setTakenTime(new Timestamp(new Date().getTime()));
			itemAdding.setTakingPlace(itemInfo.getTakingPlace());
			itemAdding.setUpdatedBy(itemInfo.getUpdatedBy());
			itemAdding.setUsername(itemInfo.getUsername());
			addItem=userItemDaoIF.addUserItem(itemAdding, username);
					
		} catch (DaoException e) {
		}

		return addItem;
	}

	public Integer deleteUserItemId(Integer itemId, String username)
			throws ServiceException {
		Integer deleteItem = RMSConstants.DELETE_USER_ITAM_PROCESSING;
		userItemDaoIF=DaoFactory.getUserItemFactory();
		try {
			deleteItem=userItemDaoIF.deleteUserItemId(itemId, username);
		} catch (DaoException e) {
		}
		return deleteItem;
	}

	public ItemInfo viewUserItemInfo(Integer itemId, String username)
			throws ServiceException {
		userItemDaoIF=DaoFactory.getUserItemFactory();
		ItemInfo itemInfo=new ItemInfo();
		try {
			itemInfo=userItemDaoIF.viewUserItemInfo(itemId, username);
		} catch (DaoException e) {
		}
		return itemInfo;
	}
	public List<ItemInfo> viewAllUserItems(String username)
			throws ServiceException {
		userItemDaoIF=DaoFactory.getUserItemFactory();
		List<ItemInfo> listOfUserItemInfo=new ArrayList<ItemInfo>();
		try {
			listOfUserItemInfo=userItemDaoIF.viewAllUserItems(username);
		} catch (DaoException e) {
		}
		return listOfUserItemInfo;
	}
	public Integer updateUserItem(ItemInfo itemInfo, String username)
			throws ServiceException {
		Integer updateItem = RMSConstants.UPDATE_USER_ITAM_PROCESSING;
		userItemDaoIF=DaoFactory.getUserItemFactory();
		return updateItem;
	}
	public List<Integer> getItemIds() throws ServiceException {
		List<Integer> listOfIds=new ArrayList<Integer>();
		itemDaoIF=DaoFactory.getItemFactory();
		try {
			listOfIds=itemDaoIF.getItemIds();
		} catch (DaoException e) {
		}
		return listOfIds;
	}

}
