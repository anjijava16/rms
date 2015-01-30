package com.iwinner.rms.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.model.ItemInfo;
import com.iwinner.rms.utils.HibernateUtils;

public class UserItemDaoImpl implements UserItemDaoIF {

	public String addUserItem(ItemInfo itemInfo, String username)
			throws DaoException {
		String addItem = RMSConstants.ADD_USER_ITEM_PROCESSING;
		Session session = HibernateUtils.getSession();
		session.beginTransaction().begin();
		try {
			System.out.println("GetItemId:::>>>>>"+itemInfo.getItemId());
			ItemInfo item = (ItemInfo) session.get(ItemInfo.class,	itemInfo.getItemId());
			if (item == null) {
				itemInfo.setItemTakenPerson(username);
				itemInfo.setUpdatedBy(username);
				session.save(itemInfo);
				addItem = RMSConstants.ADD_USER_ITEM_INSETED;
				session.beginTransaction().commit();
			} else {
				addItem = RMSConstants.ADD_USER_ITEM_EXISTS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			addItem = RMSConstants.ADD_USER_ITEM_ERROR;
			session.beginTransaction().rollback();
			throw new DaoException();
		}
		return addItem;
	}

	public Integer deleteUserItemId(Integer itemId, String username)
			throws DaoException {
		Integer deleteItem = RMSConstants.DELETE_USER_ITAM_PROCESSING;
		Session session = HibernateUtils.getSession();
		session.beginTransaction().begin();
		try {
			ItemInfo item = (ItemInfo) session.get(ItemInfo.class, itemId);
			if(item.getItemTakenPerson().equals(username)){
			if (item != null) {
				session.delete(item);
				session.beginTransaction().commit();
				deleteItem = RMSConstants.DELETE_USER_ITAM_SUCCESS;
			 }
			deleteItem = RMSConstants.DELETE_USER_ITAM_NOT_FOUND;
			}else{
				deleteItem = RMSConstants.DELETE_USER_ITAM_NOT_FOUND;
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.beginTransaction().rollback();
			deleteItem = RMSConstants.DELETE_USER_ITAM_FAILED;
			throw new DaoException();
		}
		return deleteItem;
	}	

	public ItemInfo viewUserItemInfo(Integer itemId, String username)
			throws DaoException {
		Session session = HibernateUtils.getSession();
		ItemInfo userItem=new ItemInfo();
		ItemInfo itemInfo = (ItemInfo) session.get(ItemInfo.class, itemId);
		if(itemInfo.getItemTakenPerson().equals(username)){
			try {
				BeanUtils.copyProperties(userItem, itemInfo);
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			}
		}
		return userItem;
	}
	public List<ItemInfo> viewAllUserItems(String username) throws DaoException {
		List<ItemInfo> listOfUserItemInfo=new ArrayList<ItemInfo>();
		Session session=HibernateUtils.getSession();
		Query query=session.createQuery(RMSConstants.SELECT_USER_ITEM_INFO);
		query.setString("ItemTakenPersonName", username);
		listOfUserItemInfo=query.list();
		return listOfUserItemInfo;
	}

	public Integer updateUserItem(ItemInfo itemInfo, String username)
			throws DaoException {
		Integer updateItem = RMSConstants.UPDATE_USER_ITAM_PROCESSING;
		Session session = HibernateUtils.getSession();
		session.beginTransaction().begin();
		try {
			ItemInfo item = (ItemInfo) session.get(ItemInfo.class,itemInfo.getItemId());
			if (item != null&&item.getItemTakenPerson().equals(username)) {
				session.update(item);
				updateItem = RMSConstants.UPDATE_USER_ITAM_SUCCESS;
			}else{
				updateItem = RMSConstants.UPDATE_USER_ITAM_NOT_FOUND;
			}
		} catch (Exception e) {
			session.beginTransaction().rollback();
			updateItem = RMSConstants.UPDATE_USER_ITAM_FAILED;
			throw new DaoException();
		}
		return updateItem;
	}
}
