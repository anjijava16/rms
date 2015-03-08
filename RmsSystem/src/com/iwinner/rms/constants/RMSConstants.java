package com.iwinner.rms.constants;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RMSConstants {
	public static ResourceBundle CONFIG = ResourceBundle.getBundle("Config");
    public static String SPRING_CONFIG="com//iwinner//rms//spring//config//proceduresBeans.xml";
	// Account status
	public static String ACCOUNT_ACTIVE = "ACTIVE";
	public static String ACCOUNT_IN_ACTIVE = "INACTIVE";
	public static String ACCOUNT_DISABLE = "DISABLE";
    // Account status Codes
	public static Integer ACCOUNT_ACTIVE_ID =10;
	public static Integer ACCOUNT_IN_ACTIVE_ID = 11;
	public static Integer ACCOUNT_DISABLE_ID = 12;
	public static Integer ACCOUNT_STATUS_PROCESSING=14;
    
	//Login Info
	public static String USERNAME="USERNAME";
	public static String PASSWORD="PASSWORD";
	public static String USERNAME_NULL="Username cann't be null";
	public static String USERNAME_EMPTY="Username cann't be empty";
	public static String PASSWORD_NULL="Password cann't be null";
	public static String PASSWORD_EMPTY="Password cann't be empty";
	public static String EMPTY_MESSAGE="";
	public static Integer USERNAMEANDPASSWORD_CORRECT=10;
	public static Integer USERNAMEANDPASSWORD_INCORRECT=11;
	public static Integer USERNAME_PASSWORD_VERIFICATION_PROCESSING=14;
	
	public static Integer PASSWORD_NOT_EXPIRE=10;
	public static Integer PASSWORD_EXPIRE=11;
	public static Integer PASSWORD_VERFICATION_PROCESSING=14;

	public static String LOGIN_NAVIGATION="Login";
	public static String NEW_REGISTRATION="New Registration";
	public static String FORGOT_PASSWORD="Forgot Password";
	
	
	// Default Consuective LoginFailures
	public static Integer CONSUECTIVE_LOGINFAILURES = 0;

	// Default Password Expirate time
	public static Integer PASSWORD_EXPIRE_TIMES = 0;

	// User Roles
	public static String ADMIN = "ADMIN";
	public static String NORAM_USER = "NORAMUSER";
	public static String IT_USER = "ITUSER";
  // User ROles Codes
	public static Integer ADMIN_ID=10;
	public static Integer NORAM_USER_ID=11;
	public static Integer IT_USER_ID=12;
	public static Integer ROLE_PROCESSING=14;

	public static String PASSWORD_EXPIRE_MESSAGE="Your Password expire ,Please contact admin";
	public static String PASSWORD_IN_ACTIVE="Your Password inactive,Please contact admin";
	public static String IN_VALID_CREDS="Invalid Creds,Please enter valid cred's";
	
	
	// Default Comments
	public static String ACCOUNT_CREATION_COMMENTS = "Your account is creating";

	// User Creation Codes
	public static Integer USER_CREATION_STATED = 10;
	public static Integer NEW_USER = 11;
	public static Integer USER_EXISTED = 12;
	public static Integer NEW_USER_ERROR = 13;

	public static Integer ADD_ONE = 1;
	//InActive Information
	public static Integer DEFAULT_INACTIVE=0;
	//last loginTime
	public static Integer LAST_LOGIN_PROCESSING=10;
	//No Of ExpireDates
	public static Integer NO_OF_EXPIRE_PROCESSING_DEFAULT=0;
	
	//Password forgot
	public static String FORGOT_PASSWORD_PROCESSING="Forgot Password is Processing";
	public static String FORGOT_PASSWORD_CHANGED="Your Passord Changed ,Please check Your mail";
	public static String FORGOT_PASSWORD_PROCESSING_ERROR="Forgot Password Processing error ";
	public static String FORGOT_PASSWORD_INVALID_USER="Please Enter valid User";
	
	//Password RESET
	public static String RESET_PASSWORD_PROCESSING="Reset Password is processing";
	public static String RESET_PASSWORD_CHANGED="Password Reset Sucessfully completed";
	public static String RESET_PASSWORD_ERROR="Password Reset Failed,Plese try again";

	// ItemInfo addedInfo
	public static String ADD_ITEM_PROCESSING="Add Item Id is processing";
	public static String ADD_ITEM_INSETED="Item Id is added successfully";
	public static String ADD_ITEM_ERROR="Item Id added failed,Please try again";
	public static String ADD_ITEM_EXISTS="Item Id is alredy existed";
	public static String ADD_ITEM_USER_ID_NOT_FOUND="User Name is not found,Please close the session and try again";
	
	// ItemInfo deleteInfo
	public static Integer DELETE_ITAM_PROCESSING=9;
	public static Integer DELETE_ITAM_SUCCESS=10;
	public static Integer DELETE_ITAM_NOT_FOUND=11;
	public static Integer DELETE_ITAM_FAILED=14;
	
	// ItemInfo updateInfo
	public static Integer UPDATE_ITAM_PROCESSING=9;
	public static Integer UPDATE_ITAM_SUCCESS=10;
	public static Integer UPDATE_ITAM_NOT_FOUND=11;
	public static Integer UPDATE_ITAM_FAILED=14;
	public static String UPDATE_ITEM_SUCCESS_="Item id updated successfully I";
	public static String UPDATE_ITEM_NOT_FOUND_="Item id Not successfully";
	public static String UPDATE_ITEM_FAILED_="Item id not updated ,Please try again";
	
	// UserItemInfo addedInfo
	public static String ADD_USER_ITEM_PROCESSING="Add Item Id is processing";
	public static String ADD_USER_ITEM_INSETED="Item Id is added successfully";
	public static String ADD_USER_ITEM_ERROR="Item Id added failed,Please try again";
	public static String ADD_USER_ITEM_EXISTS="Item Id is alredy existed";
	
	// UserItemInfo deleteInfo
	public static Integer DELETE_USER_ITAM_PROCESSING=9;
	public static Integer DELETE_USER_ITAM_SUCCESS=10;
	public static Integer DELETE_USER_ITAM_NOT_FOUND=11;
	public static Integer DELETE_USER_ITAM_FAILED=14;
	
	public static String DELETE_USER_ITAM_PROCESSING_="Delete Id id processing Please wait ";
	public static String DELETE_USER_ITAM_SUCCESS_="Item Id deleted successfully";
	public static String DELETE_USER_ITAM_NOT_FOUND_="Item Id Not found";
	public static String DELETE_USER_ITAM_FAILED_="Item Id deleted failed ,Please contact system admin";
	
	
	//Register Processing
	public static String REGISTER_PROCESSING="Register Processing";
	
	// UserItemInfo updateInfo
	public static Integer UPDATE_USER_ITAM_PROCESSING=9;
	public static Integer UPDATE_USER_ITAM_SUCCESS=10;
	public static Integer UPDATE_USER_ITAM_NOT_FOUND=11;
	public static Integer UPDATE_USER_ITAM_FAILED=14;	
	
	
	//TaskInfo Messages
    public static Integer NEW_TASK_ID_CREATED=10;
    public static Integer NEW_TASK_ID_PROCESSING=9;
    public static Integer NEW_TASK_ID_EXISTS=11;
    public static Integer NEW_TASK_ID_FAILED=12;
    public static Integer SESSION_NULL=20;
	
	
	// RMS Actions
	public static String ITEM_ADD="addItem.action";
	public static String ITEM_DELETE="deleteItem.action";
	public static String ITEM_UPDATE="updateItem.action";
	public static String ITEM_VIEW="viewAllItems.action";
	public static String ITEM_VIEW_ALL="viewAllItems.action";
	public static String INSERT_ITEM="insertItem.action";
	public static String ITEM_ID_INFO="itemIdInfo.action";//editItemInfo.action
	public static String ITEM_EDIT="editItemInfo.action";
	public static String ITEM_MODIFY_INSERTION="modifyItemInsertion.action";
	public static String ITEM_DELETE_ID="deleteItemId.action";
	
	
	public static String USER_ITEM_ADD="addUserItem.action";
	public static String USER_ITEM_DELETE="deleteUserItem.action";
	public static String USER_ITEM_UPDATE="updateUserItem.action";
	public static String USER_ITEM_VIEW="viewUserItem.action";
	public static String USER_ITEM_VIEW_ALL="viewAllUserItems.action";
	
	// Navigation ACTIOS
	public  static String REPORT_NAVIGATION="reports.action";
	public  static String SINGLE_REPORT_NAVIGATION="singleReports.action";
	public static String INDIVAL_REPORT="individualReportDownload.action";
	public static String DOWNLOAD_REPORT="downloadServlet.action";
	public static String FIND_USER_ACTION="findUser.do";
	public static String NEW_USER_REGISTRATION="newUser.action";	
	public static String LOGOUT_ACTION="logout.action";
   //#### TaskInfo 
	public static String ADD_TASK_NAVIGATION="addTaskRedirect.do";
	
	// UserInformation ACTIONS
	public static String NEW_USER_ACTION="newUser.action";
	public static String SEARCH_ACTION="searchUser.action";
	public static String VIEW_USERS_ACTION="viewUser.action";
	public static String DELETE_USER_ACTION="deleteUser.action";
	public static String UPDATE_USER_ACTION="updateUser.action";

	// TASKINFO
	
	public static Integer TASK_ID_PROCESSING=0;
	public static Integer TASK_ID_FAILED=10000000;
	
	
	public static String UNDER_DEV="underDev.action";
	// DB Querys
	public static String SELECT_USER_QUERY = CONFIG.getString("SELECT_USER_QUERY");
	public static String LOGIN_VERIFY_QUERY = CONFIG.getString("LOGIN_VERIFY_QUERY");
	public static String SELECT_EXPIRE_DATE=CONFIG.getString("SELECT_EXPIRE_DATE");
	public static String SELECT_NAME_EXPIREDATE=CONFIG.getString("SELECT_NAME_EXPIREDATE");
	public static String SELECT_ITEMS=CONFIG.getString("SELECT_ITEMS");
	public static String SELECT_USER_ITEM_INFO=CONFIG.getString("SELECT_USER_ITEM_INFO");
	public static String SELECT_MAX_ID=CONFIG.getString("SELECT_MAX_ID");
	public static String SELECT_USER_NAME_CHECK=CONFIG.getString("SELECT_USER_NAME_CHECK");
	public static String SELECT_USER_PRTOFILE=CONFIG.getString("SELECT_USER_PRTOFILE");
	public static String SELECT_USER_NAME_LIST=CONFIG.getString("SELECT_USER_NAME_LIST");
	public static String SELECT_ITEMS_IDS=CONFIG.getString("SELECT_ITEMS_IDS");
	public static String SELECT_USER_ROLE=CONFIG.getString("SELECT_USER_ROLE");
	public static String SELECT_USER_IDS=CONFIG.getString("SELECT_USER_IDS");
	public static String SELECT_HIGH_PRIPORTY=CONFIG.getString("SELECT_HIGH_PRIPORTY");
	public static String SELECT_TASKINFO=CONFIG.getString("SELECT_TASKINFO");
	public static String SELECT_COUNT_TASK_ID=CONFIG.getString("SELECT_COUNT_TASK_ID");
	
	
	
	
	
	// TaskInfo;
	public static String SELECT_MAX_TASK_ID=CONFIG.getString("SELECT_MAX_TASK_ID");
	
	// Report Header Information
	public static List<String> headersInformation=new ArrayList<String>();
	static{
		headersInformation.add("ItemId");
		headersInformation.add("ItemName");
		headersInformation.add("ItemPrice");
		headersInformation.add("ItemTakenPlace");
		headersInformation.add("ItemTakenDate");
		headersInformation.add("ItemTakenTime");
		headersInformation.add("ItemTakenPersonName");
		headersInformation.add("ItemTakenAlongWith");
		headersInformation.add("Comments");
	}
	
	public static List<String> listOfTaskPriportiy=new ArrayList<String>();
	
	public static  String DOWNLOAD_SAMPLE_REPORT="E:\\Tech_Learning\\saiWork_space\\Ndmns_OutPut\\NDMNSSUCCESS";
}

