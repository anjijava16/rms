package com.hbc.vp.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.validation.Errors;

import com.hbc.vp.commons.VPTConstants;
import com.hbc.vp.forms.AddInternalUserForm;
import com.hbc.vp.forms.ManageInternalUserForm;
import com.hbc.vp.forms.SearchUserForm;
import com.hbc.vp.utils.PasswordUtil;
/**
 *  @author    : SPI
 *
 * File Name   : ValidateUser.java
 *
 * Description : The Validator class for the User management.
 *
*/
public class ValidateUser {
    private static final Logger LOGGER = Logger.getLogger(ValidateUser.class);

    private static final String VALIDATION_CHARCTERS_ALLOWED = "Please enter character allowed";
    private static final String KEY_VALIDATION_NAME = "validation.name.is.incorrect";
    private static final String VALIDATION_MANDATORY_FIELD = "Mandatory Field";
    private static final String VALIDATION_DATE_RANGE_FROM_TO_DATE =
        "'From' Date cannot be greater than 'To' Date";
    private static final String VALIDATION_INVALID_PASSWORD = "Invalid Password";
    private static final String VALIDATION_INVALID_USERNAME_PASSWORD =
        "Invalid User Name or Password";
    private static final String INVALID_DATE_FORMAT =
        "Invalid Date Format";
	private static final Pattern EMAIL_PATTERN = Pattern
	
	// E111 changes email validation
	.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*(\\.[A-Za-z0-9]{2,})$");
	//.compile("[a-zA-Z0-9_]+\\.?[a-zA-Z0-9_]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+\\.?[a-zA-Z0-9]+");
	/**
     * Method Name       : validate
     *
     * Description       : This Method  is used for validation for search user screen.
     *
     * @param            : SearchUserForm usr
     *
     * @param            : Errors errors
     */
    public void validate(SearchUserForm usr, Errors errors) {

        LOGGER.debug("Inside Validate Method");

        if ((!isValidEmailAddress(usr.getEmailId()))
                && (!StringUtils.isBlank(usr.getEmailId()))) {
            errors.rejectValue("emailId", "validation.email.id.is.incorrect",
                    "The Email Address you entered is incorrect");
        }
        /**
         * If FirstName is Not Blank and (Does not contain Alphabets or contains
         * numeric values) Throw Error.
         */
        if ((!StringUtils.isBlank(usr.getFirstName()))
                && ((!StringUtils.isAlpha(usr.getFirstName())
                        || (StringUtils.isNumeric(usr.getFirstName()))))) {

            if (StringUtils.containsAny(usr.getFirstName(),
                    VPTConstants.nameFieldArray)
                    || StringUtils.contains(usr.getFirstName(), "'")) {
                errors.rejectValue("firstName", KEY_VALIDATION_NAME,
                        VALIDATION_CHARCTERS_ALLOWED);
                LOGGER.debug("Validation Occured usr.getUserId()");
            } else if(StringUtils.isAlphanumeric(usr.getFirstName())) {

                errors.rejectValue("firstName", KEY_VALIDATION_NAME,
                VALIDATION_CHARCTERS_ALLOWED);
                LOGGER.debug("Validation Occured usr.getUserId()");
            }
        }

        if ((!StringUtils.isBlank(usr.getLastName()))
                && (!StringUtils.isAlpha(usr.getLastName())
                        || (StringUtils.isNumeric(usr.getLastName())))) {

            if (StringUtils.containsAny(usr.getLastName(),
                    VPTConstants.nameFieldArray)
                    || StringUtils.contains(usr.getLastName(), "'")) {
                errors.rejectValue("lastName", KEY_VALIDATION_NAME,
                        VALIDATION_CHARCTERS_ALLOWED);
                LOGGER.debug("Validation Occured usr.getUserId()");
            } else if(StringUtils.isAlphanumeric(usr.getLastName()))
            {
                errors.rejectValue("lastName", KEY_VALIDATION_NAME,
                VALIDATION_CHARCTERS_ALLOWED);
                LOGGER.debug("Validation Occured usr.getUserId()");
            }
        }

        if (usr.getStartDateFrom() != null) {
			if (usr.getStartDateTo() == null) {
				if (errors.getFieldErrorCount("startDateTo") >= 1) {
					errors.rejectValue("startDateFrom",
							"typeMismatch.java.util.Date", INVALID_DATE_FORMAT);
				} else {
					errors.rejectValue("startDateFrom", "startto.required",
							VALIDATION_MANDATORY_FIELD);
				}
			}
		}

        if(usr.getStartDateTo() != null){
            if(usr.getStartDateFrom() == null){
            	if (errors.getFieldErrorCount("startDateFrom") >= 1) {

            	} else {
					errors.rejectValue("startDateFrom", "startto.required",
							VALIDATION_MANDATORY_FIELD);
				}
            }
        }

        if(usr.getStartDateFrom() != null &&
                usr.getStartDateTo() != null &&
                (usr.getStartDateFrom().compareTo(usr.getStartDateTo()) > 0)){
            errors.rejectValue("startDateFrom", "startfrom.cannot.lessthan.startto",
                    VALIDATION_DATE_RANGE_FROM_TO_DATE);
        }
        if (errors.getFieldErrorCount("startDateFrom") ==0
				&& errors.getFieldErrorCount("startDateTo") >= 1) {
//			System.out.println("its here");
			errors.rejectValue("startDateFrom", "typeMismatch.java.util.Date",
					INVALID_DATE_FORMAT);
		}

        if(usr.getEndDateFrom() != null){
            if(usr.getEndDateTo() == null){
                errors.rejectValue("endDateFrom", "endto.required", VALIDATION_MANDATORY_FIELD);
            }
        }

        if(usr.getEndDateTo() != null){
            if(usr.getEndDateFrom() == null){
                errors.rejectValue("endDateFrom", "endfrom.required", VALIDATION_MANDATORY_FIELD);
            }
        }
        if(usr.getEndDateFrom() != null &&
                usr.getEndDateTo() != null &&
                (usr.getEndDateFrom().compareTo(usr.getEndDateTo()) > 0)){
            errors.rejectValue("endDateFrom", "endfrom.cannot.lessthan.endto",
                    VALIDATION_DATE_RANGE_FROM_TO_DATE);
        }




        if(usr.getCreatedDateFrom() != null){
            if(usr.getCreatedDateTo() == null){
                errors.rejectValue("createdDateFrom", "createddateto.required",
                        VALIDATION_MANDATORY_FIELD);
            }
        }

        if(usr.getCreatedDateTo() != null){
            if(usr.getCreatedDateFrom() == null){
                errors.rejectValue("createdDateFrom", "createddatefrom.required",
                        VALIDATION_MANDATORY_FIELD);
            }
        }
        if(usr.getCreatedDateFrom() != null &&
                usr.getCreatedDateTo() != null &&
                (usr.getCreatedDateFrom().compareTo(usr.getCreatedDateTo()) > 0)){
            errors.rejectValue("createdDateFrom", "createdfrom.cannot.lessthan.createdto",
                    VALIDATION_DATE_RANGE_FROM_TO_DATE);
        }



        if(usr.getModifiedDateFrom() != null){
            if(usr.getModifiedDateTo() == null){
                errors.rejectValue("modifiedDateFrom", "modifieddateto.required",
                        VALIDATION_MANDATORY_FIELD);
            }
        }

        if(usr.getModifiedDateTo() != null){
            if(usr.getModifiedDateFrom() == null){
                errors.rejectValue("modifiedDateFrom", "modifieddatefrom.required",
                        VALIDATION_MANDATORY_FIELD);
            }
        }
        if(usr.getModifiedDateFrom() != null &&
                usr.getModifiedDateTo() != null &&
                (usr.getModifiedDateFrom().compareTo(usr.getModifiedDateTo()) > 0)){
            errors.rejectValue("modifiedDateFrom", "modifiedfrom.cannot.lessthan.modifiedto",
                    VALIDATION_DATE_RANGE_FROM_TO_DATE);
        }

    }
    /**
     * Method Name       : validatePassword
     *
     * Description       : This Method  is used for validation of password for the login screen.
     *
     * @param            : HashMap<String, String> passwordInfo
     *
     * @param            : Errors errors
     */
    public void validatePassword(HashMap<String, String> passwordInfo, Errors errors) {


        HashMap<String, String> passwordDetails=(HashMap<String,String>)passwordInfo;
        String currPassword=passwordDetails.get(VPTConstants.CURRENT_PASSWORD);
        String newPassword=passwordDetails.get(VPTConstants.NEW_PASSWORD);
        String reTypeNewPassword=passwordDetails.get("RETYPE_NEWPASSWORD");

        if (newPassword.equals(reTypeNewPassword)) {
            LOGGER.debug("Password Not equal " + currPassword + " " + newPassword + " " +
                    reTypeNewPassword);
            if(!PasswordUtil.validatePassword(newPassword)){
                errors.rejectValue("newPassword", "newPasswordRules.invalid",
                        VALIDATION_INVALID_PASSWORD);
            }
        } else {
            errors.rejectValue("newPassword", "newPassword.invalid", VALIDATION_INVALID_PASSWORD);
        }

    }
    /**
     * Method Name       : validateInternalUserDetails
     *
     * Description       : This Method  is used for validation of add internal user screen.
     *
     * @param            : AddInternalUserForm internalUserFormData
     *
     * @param            : Errors errors
     */
    public boolean validateInternalUserDetails(AddInternalUserForm internalUserFormData,
            Errors errors) {
        boolean x = false;
        LOGGER.debug("User ID in Validate  ::::" + internalUserFormData.getUserId());

        if (internalUserFormData.getUserId().equals("")) {
            x = true;
            errors.rejectValue("userId", "userid.mandatory", "Please enter User Name");
            LOGGER.debug("Validation Occured usr.getUserId()");
        } else
            errors = null;
        return x;
    }
    /**
     * Method Name       : validateInternalUserDetails
     *
     * Description       : This Method  is used for validation of add internal user screen.
     *
     * @param            : AddInternalUserForm internalUserFormData
     *
     * @param            : Errors errors
     */
    public boolean validateUserDateDetails(AddInternalUserForm internalUserFormData,
            Errors errors) {
        boolean x = false;
        LOGGER.debug("Inside validateUserDateDetails Method");
        LOGGER.debug("StartDate in Validate  ::::" + internalUserFormData.getStartDate());
        if (internalUserFormData.getStartDate().equals("")) {
            x = true;
            errors.rejectValue("startDate", "validation.startDate.cannot.be.null",
                    VALIDATION_MANDATORY_FIELD);
            LOGGER.debug("Validation Occured usr.getStartDate()");
        }
        return x;
    }
    /**
     * Method Name       : validateUserRoleDetails
     *
     * Description       : This Method  is used for validation of add internal user screen.
     *
     * @param            : ManageInternalUserForm internalUserFormData
     *
     * @param            : Errors errors
     */
    public boolean validateUserRoleDetails(ManageInternalUserForm internalUserFormData,
            Errors errors) {
        boolean x = false;
        LOGGER.debug("Inside validateUserDateDetails Method");
        LOGGER.debug("Role in Validate  ::::" + internalUserFormData.getRole());
        if (internalUserFormData.getRole().equals("")) {
            x = true;
            errors.rejectValue("role", "validation.role.cannot.be.null",
                    VALIDATION_MANDATORY_FIELD);
            LOGGER.debug("Validation Occured usr.getRole()");
        } else
            errors = null;
        return x;
    }
    /**
     * Method Name       : validateUserIdAndPWD
     *
     * Description       : This Method  is used for validation of add internal user screen.
     *
     * @param            : Map<String, String> loginInfo
     *
     * @param            : Errors errors
     */
    public static void validateUserIdAndPWD(Map<String, String> loginInfo, Errors errors) {
        HashMap<String, String> logDetails = (HashMap<String, String>) loginInfo;
        String userId = logDetails.get(VPTConstants.LOGIN_ID);
        String password = logDetails.get(VPTConstants.LOGIN_PASSWORD);
        if(userId == null || (userId.trim().length() ==0)){
            errors.rejectValue("password", "validation.invalid.userid.pwd",
                    VALIDATION_INVALID_USERNAME_PASSWORD);
        } else if(password == null || (password.trim().length() == 0)){
            errors.rejectValue("password", "validation.invalid.userid.pwd",
                    VALIDATION_INVALID_USERNAME_PASSWORD);
        }
    }

    /**
     * Method Name       : checkInternalUserForm
     *
     * Description       : This Method  is used for validation of add internal user screen.
     *
     * @param            : ManageInternalUserForm form
     *
     * @param            : Errors errors
     */
    public static void checkInternalUserForm(ManageInternalUserForm form,Errors errors){
        if(form.getRole().equals("-1")){
            errors.rejectValue("role", "role.mandatory", VALIDATION_MANDATORY_FIELD);
        }
        if(form.getStartDate() == null){
        	if(errors.getFieldErrorCount("startDate")<1){
            errors.rejectValue("startDate", "startdate.mandatory", VALIDATION_MANDATORY_FIELD);
        	}
        }
        if (form.getStartDate() != null) {
			if (form.getEndDate() != null
					&& (form.getStartDate().compareTo(form.getEndDate()) > 0)) {
				errors.rejectValue("endDate",
						"endDate.cannot.lessthan.startdate",
						VALIDATION_DATE_RANGE_FROM_TO_DATE);
			}
		}
        if(form.getFirstName() == null || form.getFirstName().trim().length() < 1 ||
                form.getEmailId() == null || form.getEmailId().trim().length() < 1){
            errors.rejectValue("userId", "validation.userdata.cannot.be.null",
                    VALIDATION_MANDATORY_FIELD);
        }
    }
    
	private static boolean isValidEmailAddress(String email) {
		return (StringUtils.isNotBlank(email)) ? EMAIL_PATTERN.matcher(email)
				.matches() : false;
	}



}
