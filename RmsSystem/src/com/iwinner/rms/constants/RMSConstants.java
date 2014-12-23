package com.iwinner.rms.constants;

import java.util.ResourceBundle;

public class RMSConstants {
	public static ResourceBundle CONFIG = ResourceBundle.getBundle("Config");

	// Account status
	public static String ACCOUNT_ACTIVE = "ACTIVE";
	public static String ACCOUNT_IN_ACTIVE = "INACTIVE";
	public static String ACCOUNT_DISABLE = "DISABLE";

	// Default Consuective LoginFailures
	public static Integer CONSUECTIVE_LOGINFAILURES=0;
	
	//Default Password Expirate time
	public static Integer PASSWORD_EXPIRE_TIMES=0;
	
	//User Roles
	public static String ADMIN="ADMIN";
	public static String NORAM_USER="NUSER";
	public static String IT_USER="ITUSER";
	
	//Default Comments
	public static String ACCOUNT_CREATION_COMMENTS="Your account is creating";
	
	//User Creation Codes
	public static Integer USER_CREATION_STATED=10;
	public static Integer NEW_USER=11;
	public static Integer USER_EXISTED=12;
	public static Integer NEW_USER_ERROR=13;
	
	
	
	public static Integer ADD_ONE = 1;

	
	//DB Querys
	public static String SELECT_USER_QUERY=CONFIG.getString("SELECT_USER_QUERY");
	
}
