package com.iwinner.rms.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.model.Audit;
import com.iwinner.rms.utils.HibernateUtils;

public class AuditStartUpDaoImpl implements AuditStartUpDaoIF {
	public void peristVistiorInfo(Audit aditInfo) throws DaoException {
		Session session = HibernateUtils.getSession();
	    Transaction transaction=session.beginTransaction();
	    transaction.begin();
	    Integer INCREMENT_ID= getMaxId();
	    if(INCREMENT_ID!=null){
		    aditInfo.setId(INCREMENT_ID+RMSConstants.ADD_ONE);
		    session.save(aditInfo);
		    transaction.commit();
	    }else{
	         //Initial value saving into the 
	    	aditInfo.setId(RMSConstants.ADD_ONE);
		    session.save(aditInfo);
		    transaction.commit();
	    	}
	    }

	public  Integer getMaxId()throws DaoException{
		Session session=HibernateUtils.getSession();
		Query query=session.createQuery("select Max(id) from  Audit");
		List<Integer> listOfMax=query.list();
		Integer maxValue=listOfMax.get(0);
		return maxValue;
	}
}
