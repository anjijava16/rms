package com.iwinner.rms.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.dao.RegisterDaoIF;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.helper.IdGenerator;
import com.iwinner.rms.helper.PasswordEncoder;
import com.iwinner.rms.model.UserRole;
import com.iwinner.rms.model.Users;
import com.iwinner.rms.utils.DateUtils;
import com.iwinner.rms.utils.HibernateUtils;

public class RegisterDaoImpl implements RegisterDaoIF {

	public Integer registerUser(Users user) throws DaoException {
		Integer newUser = RMSConstants.USER_CREATION_STATED;
/*		
        Users users = new Users();
		users.setAccountStatus(RMSConstants.ACCOUNT_ACTIVE);
		users.setConsecutiveLoginFailures(RMSConstants.CONSUECTIVE_LOGINFAILURES);
		users.setEmail("badmin@gmail.com");
		users.setExpirationDate(DateUtils.expireDate());
		users.setExpirePassword(RMSConstants.PASSWORD_EXPIRE_TIMES);
		users.setFullName("badmin");
		users.setLastLogin(new Timestamp(new Date().getTime()));
		users.setLastModifiedTime(new Timestamp(new Date().getTime()));
		users.setLastPasswordChangedDate(new Date());
		users.setLastUpdatedBy("badmin");
		users.setPastPasswords(PasswordEncoder.encodePassword("badmin"));
		users.setPassword(PasswordEncoder.encodePassword("badmin"));
		users.setPhone("9009134521");
		users.setRole(RMSConstants.ADMIN);
		users.setUserComments(RMSConstants.ACCOUNT_CREATION_COMMENTS);
		users.setUserId(Integer.parseInt(IdGenerator.randId()));
		users.setUsername("badmin");
		
 	*/
		
		Session session = HibernateUtils.getSession();
		session.beginTransaction().begin();
		Users userN = (Users) session.get(Users.class, user.getUsername());
		if (userN == null) {
			session.save(user);
			session.beginTransaction().commit();
			newUser = RMSConstants.NEW_USER;
		} else {
			newUser = RMSConstants.USER_EXISTED;
		}
		return newUser;
	}

public List<UserRole> getUserRole()throws DaoException{
	List<UserRole> listOfUser=new ArrayList<UserRole>();
	Session session = HibernateUtils.getSession();
    Query query=session.createQuery(RMSConstants.SELECT_USER_ROLE);
    listOfUser=query.list();
	return listOfUser;
}

public List<String> getUserNames()throws DaoException{
	List<String> listOfUserNames=new ArrayList<String>();
	Session session = HibernateUtils.getSession();
    Query query=session.createQuery(RMSConstants.SELECT_USER_IDS);
    listOfUserNames=query.list();
    return listOfUserNames;
}
public static void main(String[] args) {

		RegisterDaoImpl rD = new RegisterDaoImpl();
		try {
			//Integer newUser = rD.registerUser(new Users());
			//List<String> listOF=rD.getUserNames();
			List<UserRole> listOfUser=rD.getUserRole();
             for(UserRole uss:listOfUser){
            	 System.out.println(uss.getId()+"  "+uss.getRoleName());
             }
		} catch (DaoException e) {
		}
	}

}
