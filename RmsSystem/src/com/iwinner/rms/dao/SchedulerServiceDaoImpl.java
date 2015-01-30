package com.iwinner.rms.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.model.Users;
import com.iwinner.rms.utils.HibernateUtils;

public class SchedulerServiceDaoImpl implements SchedulerServiceDaoIF {

	public Object getExpireUserDetails() throws DaoException {
		Session session = HibernateUtils.getSession();
		Query query = session.createSQLQuery(RMSConstants.SELECT_EXPIRE_DATE);
		List<String> listOfUsers = query.list();
		List<String> listOfUserInfo = new ArrayList<String>();
		if (listOfUsers.size() > 1) {
			for (String users : listOfUsers) {
				listOfUserInfo.add(users);
			}
		} else if (listOfUsers.size() == 1) {
			listOfUserInfo.add(listOfUsers.get(0));
		} else {

		}
		return listOfUserInfo;
	}

	public Integer updateNoOfUsersIncative() throws DaoException {
		Integer noOfUserIncative = RMSConstants.DEFAULT_INACTIVE;
		Session session = HibernateUtils.getSession();
		List<String> listOfInactiveUsers;
		String username = "";
		try {
			listOfInactiveUsers = (List<String>) getExpireUserDetails();
			session.beginTransaction().begin();
			Users users = new Users();
			if (listOfInactiveUsers.size() > 1) {
				Iterator it = listOfInactiveUsers.iterator();
				while (it.hasNext()) {
					username = (String) it.next();
					users = (Users) session.get(Users.class, username);
					users.setAccountStatus(RMSConstants.ACCOUNT_IN_ACTIVE);
					users.setLastModifiedTime(new Timestamp(new Date()
							.getTime()));
					session.update(users);
					session.beginTransaction().commit();
					noOfUserIncative++;
				}
			} else if (listOfInactiveUsers.size() == 1) {
				users = (Users) session.get(Users.class, username);
				users.setAccountStatus(RMSConstants.ACCOUNT_IN_ACTIVE);
				session.update(users);
				session.beginTransaction().commit();
				noOfUserIncative++;
			} else {
				noOfUserIncative = RMSConstants.DEFAULT_INACTIVE;
				;
			}

		} catch (DaoException e) {
		}

		return noOfUserIncative;
	}

	public List<Users> passwordExpireDays() {
		Session session = HibernateUtils.getSession();
		Query query = session.createQuery(RMSConstants.SELECT_NAME_EXPIREDATE);
		List<Users> listOfUser = query.list();
		return listOfUser;
	}

	public Integer updatePasswordExpireDays() throws DaoException {
		Integer noOfRecordsExpirePasswordDays = RMSConstants.NO_OF_EXPIRE_PROCESSING_DEFAULT;
		Session session = HibernateUtils.getSession();
		List<Users> listOfUsers = (List<Users>) passwordExpireDays();
		Users users = new Users();
		String username = "";
		if (listOfUsers.size() > 1) {
			Iterator it = listOfUsers.iterator();
			while (it.hasNext()) {
				Users user = (Users) it.next();
				username = user.getUsername();
				Long expireDate = user.getExpirationDate().getTime();
				Long currentDate = new Date().getTime();
				Long days = expireDate - currentDate;
				Long noOfExpireDays = days / (1000 * 60 * 60 * 24);
				session.beginTransaction().begin();
				users = (Users) session.get(Users.class, username);
				users.setLastModifiedTime(new Timestamp(new Date().getTime()));
				users.setExpirePassword(noOfExpireDays.intValue());
				session.update(users);
				session.beginTransaction().commit();
				noOfRecordsExpirePasswordDays++;
			}
		} else if (listOfUsers.size() == 1) {
			for (Users user : listOfUsers) {
				username = user.getUsername();
				Long expireDate = user.getExpirationDate().getTime();
				Long currentDate = new Date().getTime();
				Long days = expireDate - currentDate;
				Long noOfExpireDays = days / (1000 * 60 * 60 * 24);
				users = (Users) session.get(Users.class, username);
				users.setLastModifiedTime(new Timestamp(new Date().getTime()));
				users.setExpirePassword(noOfExpireDays.intValue());
				session.update(users);
				session.beginTransaction().commit();
				noOfRecordsExpirePasswordDays++;
			}
		} else {

		}

		return noOfRecordsExpirePasswordDays;
	}

	public static void main(String[] args) {
		SchedulerServiceDaoImpl sd = new SchedulerServiceDaoImpl();
		// List list=(List)sd.passwordExpireDays();
		Integer days = 0;
		try {
			days = sd.updatePasswordExpireDays();
		} catch (DaoException e) {
		}
		System.out.println(days);
		/*
		 * List<String> info = new ArrayList<String>(); ; try { info = (List)
		 * sd.getExpireUserDetails(); } catch (DaoException e) { }
		 * System.out.println(info); Integer nu = sd.updateNoOfUsersIncative();
		 * System.out.println(nu);
		 */
	}
}
