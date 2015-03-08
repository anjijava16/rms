package com.iwinner.rms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.iwinner.rms.dao.PersonInformationDaoIF;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.model.PersonalInfo;
import com.iwinner.rms.utils.HibernateUtils;

public class PersonInformationDaoImpl implements PersonInformationDaoIF {

	public List<PersonalInfo> getPersonalInfo() throws DaoException {
		List<PersonalInfo> listPesronalInfo = new ArrayList<PersonalInfo>();
		return listPesronalInfo;
	}

	public Integer addPersonalInfo(PersonalInfo pInfo, Integer idValue)
			throws DaoException {
		Integer personID = 0;
		Session session = HibernateUtils.getSession();
		if (idValue == 1) {

		} else {
			personID = 15;
			session.beginTransaction().begin();
		}
		return personID;
	}

	public Integer deletePersonalInfo(Integer personalId) throws DaoException {
		Integer deletePerID = 0;
		Session session = HibernateUtils.getSession();
		PersonalInfo pInfo = (PersonalInfo) session.get(PersonalInfo.class,
				personalId);
		session.beginTransaction().begin();
		if (pInfo != null) {
			session.delete(pInfo);
			session.beginTransaction().commit();
			deletePerID = 10;
		} else {
			session.beginTransaction().rollback();
			deletePerID = 11;
		}
		return deletePerID;
	}

	public Integer getPersonID() throws DaoException {
		Session session = HibernateUtils.getSession();
		Query query = session.createQuery("");
		List<Integer> personMaxID = (List<Integer>) query.list();
		Integer pMaxID = 0;
		for (Integer pMax : personMaxID) {
			pMaxID = pMax;
		}
		return pMaxID;
	}

	public PersonalInfo getPersonalIngoPage(Integer personInfoID)
			throws DaoException {
		PersonalInfo pInfo = new PersonalInfo();
		Session session = HibernateUtils.getSession();
		pInfo = (PersonalInfo) session.load(PersonalInfo.class, personInfoID);
		return pInfo;
	}
}
