package com.iwinner.rms.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.model.Audit;
import com.iwinner.rms.utils.HibernateUtils;

public class AuditStartUpDaoImpl implements AuditStartUpDaoIF {
	public void peristVistiorInfo(Audit aditInfo) throws DaoException {
		Session session = HibernateUtils.getSession();
	    Transaction transaction=session.beginTransaction();
	    transaction.begin();
	    aditInfo.setId(1);
	    session.save(aditInfo);
	    transaction.commit();
	}
	/*

	public  Integer getMaxId()throws DaoException{
		Session session=HibernateUtils.getSession();
		Query query=session.createQuery("select Max(id) from  Audit");
		List<Integer> listOfMax=query.list();
		Integer maxValue=listOfMax.get(0);
		return maxValue;
	}
*/
}
