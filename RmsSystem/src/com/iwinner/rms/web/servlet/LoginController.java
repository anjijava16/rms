package com.hbc.vp.controllers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.naming.AuthenticationException;
import javax.naming.CommunicationException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.hbc.vp.commons.VPTConstants;
import com.hbc.vp.context.LoginFilter;
import com.hbc.vp.dtos.AnnouncementDTO;
import com.hbc.vp.dtos.LoginDTO;
import com.hbc.vp.dtos.ManualDTO;
import com.hbc.vp.exception.VPApplicationException;
import com.hbc.vp.exception.VPBusinessException;
import com.hbc.vp.forms.LoginForm;
import com.hbc.vp.forms.UserHitCountForm;
import com.hbc.vp.services.CMSService;
import com.hbc.vp.services.LoginService;
import com.hbc.vp.validators.ValidateUser;
/**
 * @author     :SPI
 *
 * File Name   :LoginController.java
 *
 * Description :This is a Controller Class for Login Screen.
 *
 */
@Controller
@RequestMapping("/Login.html")
@SessionAttributes("loginForm")
public class LoginController {
    
	private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    
    private static final String FORWARD_TO_LOGIN = "Login";
    private static final int PASSWORD_EXPIRY = 10;
    private static final String FORWARD_TO_MANAGE_EXPENSE = 
        "redirect:smf/ManageExpenseVendor.html?locale=";
    private static final String FORWARD_TO_MANAGE_VENDOR = 
        "redirect:smf/ManageVendor.html?locale=";
    private static final String FORWARD_TO_DASHBOARD = 
        "redirect:user_management/DashBoard.html?locale=";
    private static final String FORWARD_TO_USERTERMS = 
    	"redirect:user_management/UserTerms.html?locale=";
    private static final String FORWARD_TO_FORCEPASSWORD = 
    	"redirect:/user_management/ForceChangePassword.html?locale=";
    
    @Autowired
    private LoginService loginService;
    @Autowired
	private CMSService cmsService;
     /**
     * Method Name  : LoginController
     *
     * Description  :Constructor of LoginController
     */
    public LoginController() {
        LOGGER.debug("Inside LoginController Constructor");
    }

     /**
     * Method Name          :initBinder
     *
     * Description          :This method will bind the form value to the
     *                       specific field in the form
     *
     * @param dataBinder    :WebDataBinder object
     *
     * @return              :This method will not return anything
     */
    @InitBinder
    public void initBinder(final WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(
                String.class, new StringTrimmerEditor(false));
    }
    
    /**
     * Method Name          :doLogin
     *
     * Description          :This method will load the Login screen where user
     *                       have to enter valid userId and password to enter
     *                       into the application.
     *
     * @return              :LoginForm
     */
    @RequestMapping(method = RequestMethod.GET)
    @ModelAttribute("loginForm")
    public LoginForm doLogin(HttpServletRequest request) {
//        LOGGER.debug("Calling LoginForm.html inside doLogin()");
        LoginForm loginform = new LoginForm();
        Locale locale= RequestContextUtils.getLocale(request);
        LOGGER.debug("Locale from the request="+locale);
		Locale E =new Locale("en","US");
		//Locale engIndia =new Locale("en","IN");
		//Locale engCanada =new Locale("en","CA");
		Locale F =new Locale("fr","");
		
		if(locale==null || locale.equals(E))// || locale.equals(engIndia) || locale.equals(engCanada))
		{
			LOGGER.debug("Setting Flag = "+locale);
			loginform.setEngFlag(true);
		}
		else if(locale.equals(F))
		{
			loginform.setEngFlag(false);
		}else{
			LOGGER.debug("Setting Flag = "+locale);
			loginform.setEngFlag(true);
		}
		loginform.setLocale(locale);
	    loginform = cmsService.getAllPublicAnnouncementsandManualForms(loginform);
	    List<AnnouncementDTO> announcementDTOList=loginform.getAnnouncementDTOList();
	    Iterator<AnnouncementDTO> it=announcementDTOList.iterator();
	    while(it.hasNext()){
	    	AnnouncementDTO announcementDTO=it.next();
	    	if(announcementDTO.getTitleEN()!=null){
	    		loginform.getAnnouncementDTOListForEN().add(announcementDTO);
	    	}
	    	if(announcementDTO.getTitleFR()!=null){
	    		loginform.getAnnouncementDTOListForFR().add(announcementDTO);
	    	}
	    }
	    List<ManualDTO> manualDTOList= loginform.getManualDTOList();
	    Iterator<ManualDTO>it1=manualDTOList.iterator();
	    while(it1.hasNext())
	    {
	    	ManualDTO manualDTO= it1.next();
	    	if(manualDTO.getTitleEN()!=null)
	    	{
	    		loginform.getManualDTOListForEN().add(manualDTO);
	    	}
	    	if(manualDTO.getTitleFR()!=null)
	    	{
	    		loginform.getManualDTOListForFR().add(manualDTO);
	    	}
	   
	    }
	    List<ManualDTO> docGroupDiscriptionList= loginform.getDocGroupDiscriptionList();
	    Iterator<ManualDTO>it3=docGroupDiscriptionList.iterator();
	    while(it3.hasNext())
	    {
	    	ManualDTO manualDTO=it3.next();
	    	if(manualDTO.getTitleEN()!=null)
	    	{
	    		loginform.getDocGroupDiscriptionListForEN().add(manualDTO);
	    	}
	    	if(manualDTO.getTitleFR()!=null)
	    	{
	    		loginform.getDocGroupDiscriptionListForFR().add(manualDTO);
	    	}
	    	
	    }
	    
		/* if(loginform.getAnnouncementDTOList().size()==0 )
		 {
			 LOGGER.debug("AnnouncementDTOList-------->"+loginform.getAnnouncementDTOList().size());
			 loginform.setAnnouncementSize(1);
		 }*/
	    
		 /*if(loginform.getManualDTOList().size()==0 )
		 {
			 LOGGER.debug("ManualDTOList-------->"+loginform.getAnnouncementDTOList().size());
			 loginform.setManualSize(1);
		 }	
        */
	    if(loginform.isEngFlag() && loginform.getAnnouncementDTOListForEN().size()==0){
			 loginform.setAnnouncementSize(1);
		 }else if(!loginform.isEngFlag()&& loginform.getAnnouncementDTOListForFR().size()==0){
			 loginform.setAnnouncementSize(1);
		 }
		 if(loginform.isEngFlag() && loginform.getManualDTOListForEN().size()==0){
			 loginform.setManualSize(1);
		 }else if(!loginform.isEngFlag()&& loginform.getManualDTOListForFR().size()==0){
			 loginform.setManualSize(1);
		 }
		  
		 /*Added by Naveed to Indicate that session expired*/
//		 System.out.println("sessionExpiry:::::::::::::::1>"+request.getParameter("sessionExpiry"));
		 if (request.getParameter("sessionExpiry") == null) {
//			System.out.println("sessionExpiry:::::::::::::::1>"+request.getParameter("sessionExpiry"));
			 loginform.setSessionFlag(false);

		} else if (request.getParameter("sessionExpiry").equals("true")) {
//			System.out.println("sessionExpiry:::::::::::::::2>"+request.getParameter("sessionExpiry"));
			loginform.setSessionFlag(true);
		} else if (request.getParameter("sessionExpiry").equals("false")) {
//			System.out.println("sessionExpiry:::::::::::::::3>"+request.getParameter("sessionExpiry"));
			loginform.setSessionFlag(false);
		}
		 
		 
        return loginform;
    }

    
    /**
     * Method Name          :login
     *
     * Description          :This method first validate the userId and password.
     *                       When it satisfies then it will check the database
     *                       for their authenticity.Based on the result it
     *                       will load appropriate page.
     *
     *
     * @param loginForm     :LoginForm
     * @param result        :BindingResult
     * @param request       :Request Object
     *
     * @return              :String which will load the appropriate Screen.
     *
     * @catches             :VPApplicationException
     */
    @RequestMapping(params = "login", method = RequestMethod.POST)
    @ModelAttribute("loginForm")
    public String login(final LoginForm loginForm, final BindingResult result,
            final HttpServletRequest request) {
        LOGGER.debug("Inside login method after click on login button");
        String forward = "";
        String locale="";
        boolean authUser = false;
        boolean passwordChangeFlag=false;
        Locale localeF= RequestContextUtils.getLocale(request);
        Locale F =new Locale("fr","");
        try {
            final Map<String, String> loginInfo = new HashMap<String, String>();
            loginInfo.put(VPTConstants.LOGIN_ID, loginForm.getUserName().trim());
            loginInfo.put(VPTConstants.LOGIN_PASSWORD, loginForm.getPassword().trim());
            ValidateUser.validateUserIdAndPWD(loginInfo, result);
            if (result.hasErrors()) {
                forward = FORWARD_TO_LOGIN;
            }
            else {
             try {
                    authUser = loginService.authenticateUser(loginForm, result);
                    LOGGER.debug("authUser=============> " + authUser);
                }catch (CommunicationException e) {
                    LOGGER.error(e.getMessage(),e);
                    result.rejectValue("password","ad.server.busy","AD server is busy");
                    forward = FORWARD_TO_LOGIN;
                }catch (AuthenticationException e) {
                    LOGGER.error(e.getMessage(),e);
                    result.rejectValue("password","validation.invalid.userid.pwd",
                  		"Invalid User Name or Password");
                    forward = FORWARD_TO_LOGIN;
                }catch (VPBusinessException e) {
                    LOGGER.error(e.getMessage(),e);
 //                   System.out.println("authenticateUser ********************");
//                    e.printStackTrace();
                    result.rejectValue("password","error.internal.server",
                    		"There is an Internal Server Error");
                    forward = FORWARD_TO_LOGIN;
                }
                if(result.hasErrors()){
                    forward = FORWARD_TO_LOGIN;
                }else {
                	 if (loginService.getUserStatus(loginForm.getUserName()).trim()
         					.equalsIgnoreCase(VPTConstants.STATUS_PASSWORD_CHANGE)) {
         				passwordChangeFlag = true;
         			}
                    LOGGER.debug("loginForm.getActiveDirectory() = "+ loginForm.getActiveDirectory());
                    request.getSession().setAttribute(
                            VPTConstants.DIR_CONTEXT,
                            loginForm.getActiveDirectory());
                    final LoginDTO loginDTO = loginService.getLoginUserInfo(
                            loginInfo.get(VPTConstants.LOGIN_ID));
//                    LOGGER.debug("loginDTO User STATUS = "+ loginDTO.getUserStatus());
                    LoginFilter.createLoginContext(request, loginDTO);
//                    LOGGER.debug("loginForm.getPasswordExpiry() = "+ loginForm.getPasswordExpiry());
//                    LOGGER.debug("getUserPrimaryRole = "+ loginDTO.getUserPrimaryRole());
//                    LOGGER.debug("getUserRoleId = "+ loginDTO.getUserRoleId());
//                    LOGGER.debug("getUserRoleName = "+ loginDTO.getUserRoleName());
                    if (loginDTO.getUserLanguagePreference().equals("1")) {
						locale = "en";
					} else if (loginDTO.getUserLanguagePreference().equals("2")) {
						locale = "fr";
					}
					if (localeF.equals(F)) {
						locale = "fr";
					}				
					
					/* code to insert a record in to Hit count table*/
					String ipAddress = request.getHeader("X-FORWARDED-FOR");
	                
					if (ipAddress == null) {
	                	ipAddress = request.getRemoteAddr();
	                }
	                
	                //Added for sub menu implementation
	                UserHitCountForm userHitCountForm = new UserHitCountForm();
	                userHitCountForm.setUserId(loginDTO.getUserId());
	                userHitCountForm.setIpAddress(ipAddress);
	                userHitCountForm.setUserClassificationId(loginDTO.getUserClassificationId());
	                userHitCountForm.setCompanyCd(loginDTO.getUserCompanyId());
	                userHitCountForm.setUserRoleId(loginDTO.getUserRoleId());
	                
	                // Calling service to save the use hit
	                loginService.saveHitCount(userHitCountForm);
					
                    if(loginForm.getPasswordExpiry()  <= PASSWORD_EXPIRY){
                        forward = FORWARD_TO_LOGIN;
                    } else if(loginDTO.getUserStatus().trim().equalsIgnoreCase(
                    		VPTConstants.REF_USER_NEW)){
                        forward = FORWARD_TO_USERTERMS+locale;
                    } 
                    else if (passwordChangeFlag) {
						forward = FORWARD_TO_FORCEPASSWORD + locale;
					} else if (loginDTO.getUserClassificationId().trim().equalsIgnoreCase(
							VPTConstants.TEMP_USER_ID)) {
						if (loginDTO.getSmfType() != null
								&& loginDTO.getSmfType().trim().equalsIgnoreCase(
										VPTConstants.EXPENSE_VENDOR)) {
							forward = FORWARD_TO_MANAGE_EXPENSE + locale;
						} else {
							forward = FORWARD_TO_MANAGE_VENDOR + locale;
						}
					} else {
						forward = FORWARD_TO_DASHBOARD + locale;
					}
//                    LOGGER.debug("roletype:"+loginDTO.getUserRoleTypeName());
                }
            }
        } catch (VPApplicationException vae) {
            LOGGER.error(vae.getMessage(),vae);
            forward = FORWARD_TO_LOGIN;
        }
        loginForm.setForwardString(forward);

        return forward;
    }

}
