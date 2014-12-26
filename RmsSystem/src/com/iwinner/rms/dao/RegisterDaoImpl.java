package com.iwinner.rms.dao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.helper.IdGenerator;
import com.iwinner.rms.helper.PasswordEncoder;
import com.iwinner.rms.model.Users;
import com.iwinner.rms.utils.HibernateUtils;

public class RegisterDaoImpl implements RegisterDaoIF {

	public Integer registerUser(Users user) throws DaoException {
		Integer newUser=RMSConstants.USER_CREATION_STATED;
		Users users=new Users();
		users.setAccountStatus(RMSConstants.ACCOUNT_ACTIVE);
		users.setConsecutiveLoginFailures(RMSConstants.CONSUECTIVE_LOGINFAILURES);
		users.setEmail("anjijava16@gmail.com");
		users.setExpirationDate(expireDate());
		users.setExpirePassword(RMSConstants.PASSWORD_EXPIRE_TIMES);
		users.setFullName("admin");
		users.setLastLogin(new Timestamp(new Date().getTime()));
		users.setLastModifiedTime(new Timestamp(new Date().getTime()));
		users.setLastPasswordChangedDate(new Date());
		users.setLastUpdatedBy("admin");
		users.setPassPasswords(PasswordEncoder.encodePassword("admin"));
		users.setPassword(PasswordEncoder.encodePassword("admin"));
		users.setPhone("9009134521");
		users.setRole(RMSConstants.ADMIN);
		users.setUserComments(RMSConstants.ACCOUNT_CREATION_COMMENTS);
		users.setUserId(Integer.parseInt(IdGenerator.randId()));
		users.setUsername("admin");
		
		Session session=HibernateUtils.getSession();
		session.beginTransaction().begin();
		Users userN=(Users)session.get(Users.class, users.getUsername());
		if(userN==null){
			session.save(users);
			session.beginTransaction().commit();
			newUser=RMSConstants.NEW_USER;
		}else{
			newUser=RMSConstants.USER_EXISTED;
		}
		return newUser;
	}
	public static Date expireDate(){
		Date date=new Date();
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 60);
		Date ds=cal.getTime();
		return ds;
	}
	
	public static void main(String[] args) {
		
		
	
		RegisterDaoImpl rD=new RegisterDaoImpl();
		try {
			Integer newUser=rD.registerUser(new Users());
			System.out.println(newUser);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
