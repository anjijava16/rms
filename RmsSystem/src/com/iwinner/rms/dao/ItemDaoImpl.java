package com.iwinner.rms.dao;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.model.ItemInfo;
import com.iwinner.rms.utils.HibernateUtils;

public class ItemDaoImpl implements ItemDaoIF {

	public String addItem(ItemInfo itemInfo) throws DaoException {
		String addItem = RMSConstants.ADD_ITEM_PROCESSING;
		Session session = HibernateUtils.getSession();
		session.beginTransaction().begin();
		try {
			ItemInfo item = (ItemInfo) session.get(ItemInfo.class,
					itemInfo.getItemId());
			if (item == null) {
				if(LoginDaoImpl.userNameVerify(itemInfo.getUsername())){
				session.save(itemInfo);
				addItem = RMSConstants.ADD_ITEM_INSETED;
				session.beginTransaction().commit();
				}else {
					addItem = RMSConstants.ADD_ITEM_USER_ID_NOT_FOUND;
				}
			} else {
				addItem = RMSConstants.ADD_ITEM_EXISTS;
			}
		} catch (Exception e) {
			addItem = RMSConstants.ADD_ITEM_ERROR;
			session.beginTransaction().rollback();
			throw new DaoException();
		}
		return addItem;
	}

	public Integer deleteItemId(Integer itemId) throws DaoException {
		Integer deleteItem = RMSConstants.DELETE_ITAM_PROCESSING;
		Session session = HibernateUtils.getSession();
		session.beginTransaction().begin();
		try {
			ItemInfo item = (ItemInfo) session.get(ItemInfo.class, itemId);
			if (item != null) {
				session.delete(item);
				session.beginTransaction().commit();
				deleteItem = RMSConstants.DELETE_ITAM_SUCCESS;
			} else {
				deleteItem = RMSConstants.DELETE_ITAM_NOT_FOUND;
			}

		} catch (Exception e) {
			session.beginTransaction().rollback();
			deleteItem = RMSConstants.DELETE_ITAM_FAILED;
			throw new DaoException();
		}
		return deleteItem;
	}

	public ItemInfo viewItemInfo(Integer itemId) throws DaoException {
		Session session = HibernateUtils.getSession();
		ItemInfo itemInfo = (ItemInfo) session.get(ItemInfo.class, itemId);
		return itemInfo;
	}

	public List<ItemInfo> viewAllItems() throws DaoException {
		Session session = HibernateUtils.getSession();
		Query query = session.createQuery(RMSConstants.SELECT_ITEMS);
		List<ItemInfo> listItemInfo = query.list();

		return listItemInfo;
	}

	public Integer updateItem(ItemInfo itemInfo) throws DaoException {
		Integer updateItem = RMSConstants.UPDATE_ITAM_PROCESSING;
		Session session = HibernateUtils.getSession();
		session.beginTransaction().begin();
		try {
			ItemInfo item = (ItemInfo) session.get(ItemInfo.class,
					itemInfo.getItemId());
			if (item != null) {
				session.update(item);
				updateItem = RMSConstants.UPDATE_ITAM_SUCCESS;
			}
		} catch (Exception e) {
			session.beginTransaction().rollback();
			updateItem = RMSConstants.UPDATE_ITAM_FAILED;
			throw new DaoException();
		}
		return updateItem;
	}
	public static Integer incrementItem()throws DaoException{
		Session session =HibernateUtils.getSession();
		Query query=session.createQuery(RMSConstants.SELECT_MAX_ID);
		List<Integer> listOfMax=query.list();
		Integer maxValue=listOfMax.get(0);
	
		return maxValue;
	}
	
}
