package com.hbc.vp.validators;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.validation.Errors;

import com.hbc.vp.commons.VPTConstants;
import com.hbc.vp.exception.VPApplicationException;
import com.hbc.vp.forms.AddressForm;
import com.hbc.vp.forms.ExpenseVendorForm;
import com.hbc.vp.forms.ManageVendorForm;
import com.hbc.vp.utils.FormatUtil;

/**
 * @author :SPI
 * 
 *         File Name :ValidateVendor.java
 * 
 *         Description :This is a validator class for ADD SMF. It performs
 *         validation of required fields and sets the error message when it
 *         encounters errors.
 * 
 */
public class ValidateVendor {
	private static final Logger LOGGER = Logger.getLogger(ValidateVendor.class);

	private static final String VALIDATION_MANDATORY_FIELD = "Mandatory";
	private static final String VALIDATION_VALID_NUMBER = "must be a valid number";
	private static final String VALIDATION_VALID_NUMBER_DASH = "must be a valid number and  '-'";
	private static final String VALIDATION_ONLY_ALPHABETS_ALLOWED = "only alphabets allowed";
	private static final String VALIDATION_ONLY_ALPHANUMERIC_ALLOWED = "only alphanumeric allowed";
	//private static final String VALIDATION_EMAIL_ID = "Not a valid Email Id";
	private static final String VALIDATION_EMAIL_ID = "Please enter a valid Email Address";
	private static final String VALIDATION_ALPHABETS = "Please enter only alphabets";

    // Validation message to be displayed for Site# number validation  
    private static final String VALIDATION_VALID_SITE_SIX_DIGITS_NUMBER ="Site # should be six digits";

    // Validation message to be displayed for Site# number validation of LT Vendors 
    private static final String VALIDATION_VALID_SITE_NINE_DIGITS_NUMBER ="Site # should be nine digits";
    private static final String VALIDATION_VALID_SITE_START = "Site # should start with 2";

	private static final Pattern EMAIL_PATTERN = Pattern
	// E111 changes email validation
	.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*(\\.[A-Za-z0-9]{2,})$");	
			//.compile("[a-zA-Z0-9_]+\\.?[a-zA-Z0-9_]+@[a-zA-Z0-9+[-[a-zA-Z0-9]+]]+\\.[a-zA-Z0-9]+\\.?[a-zA-Z0-9]+");
	private static final Pattern AMOUNT_PATTERN = Pattern.compile("[0-9]*?\\.?[0-9]+");

	/**
	 * Method Name : validate
	 * 
	 * Description : This Method is used for validation for search user screen.
	 * 
	 * @param : ManageVendorForm form
	 * 
	 * @param : Errors errors
	 */
	public static void validate(ManageVendorForm form, Errors errors)
			throws VPApplicationException {

		// validate site and mstr sup num only when VC in action

		boolean isRework = false;
		boolean isRestart = false;
		String statusId = form.getStatusId();
		
		LOGGER.debug("==============================================================================="+form.getBannerDropDown());
		if(form.getBannerDropDown().equals("-1")){
			errors.rejectValue("bannerDropDown", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
		}		
		
		//EDI validation is only for HBC and not for L&T -- 3495 is 'L&T Consignment' and 3496 is 'L&T Regular'
		if(!form.getStatusId().equals("2100") && !form.getStatusId().equals("2110")){
			if(!form.getVendrType().equals("3495") && !form.getVendrType().equals("3496")){
				
				if(form.getEdiContactName() == null || form.getEdiContactName().trim().equals("")){
					errors.rejectValue("ediContactName", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
				}
				if (form.getEdiTitle() == null || form.getEdiTitle().trim().equals("")) {
					errors.rejectValue("ediTitle", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
				}
				if(form.getEdiPhone() == null || form.getEdiPhone().trim().equals("")){
					errors.rejectValue("ediPhone", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
				}
				if(form.getEdiEmailId() == null || form.getEdiEmailId().trim().equals("")){
					errors.rejectValue("ediEmailId", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
				}
				if(form.getEdiFax() == null || form.getEdiFax().trim().equals("")){
					errors.rejectValue("ediFax", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
				}
				if(form.getEdiProviderType().equals("-1")){
					errors.rejectValue("ediProviderType", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
				}
				if(form.getEdiProviderType().equals("3501") && (form.getEdiProvider() == null || form.getEdiProvider().trim().equals(""))){
					errors.rejectValue("ediProvider", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
				}
				if(form.getEdiProviderType().equals("3501") && (form.getEdiPvdContactName() == null || form.getEdiPvdContactName().trim().equals(""))){
					errors.rejectValue("ediPvdContactName", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
				}
				if(form.getEdiProviderType().equals("3501") && (form.getEdiPvdTitle() == null || form.getEdiPvdTitle().trim().equals(""))){
					errors.rejectValue("ediPvdTitle", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
				}
				if(form.getEdiProviderType().equals("3501") && (form.getEdiPvdPhone() == null || form.getEdiPvdPhone().trim().equals(""))){
					errors.rejectValue("ediPvdPhone", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
				}
				if(form.getEdiProviderType().equals("3501") && (form.getEdiPvdEmailId() == null || form.getEdiPvdEmailId().trim().equals(""))){
					errors.rejectValue("ediPvdEmailId", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
				}
				if(form.getEdiProviderType().equals("3501")&& (form.getEdiPvdFax() == null || form.getEdiPvdFax().trim().equals(""))){
					errors.rejectValue("ediPvdFax", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
				}
				String[] productionAttrib = form.getEdiTest();
				if(productionAttrib[0] == null || productionAttrib[0].trim().equals("")){
					errors.rejectValue("ediTest[0]", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
				}
				if(productionAttrib[1] == null || productionAttrib[1].trim().equals("")){
					errors.rejectValue("ediTest[1]", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
				}
				if(productionAttrib[2] == null || productionAttrib[2].trim().equals("")){
					errors.rejectValue("ediTest[2]", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
				}
				if(productionAttrib[3] == null || productionAttrib[3].trim().equals("")){
					errors.rejectValue("ediTest[3]", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
					LOGGER.debug("LENGTH IS"+productionAttrib.length);
					LOGGER.debug("First Attrib is"+productionAttrib[0]);
				}
			}
		}

		if (statusId == null)
			throw new VPApplicationException("Status Id cannot be null");

		statusId = statusId.trim();

		if (form.getActionName() != null) {
			if (form.getActionName().equalsIgnoreCase(VPTConstants.REWORK)) {
				isRework = true;
			}
			if (form.getActionName().equalsIgnoreCase(
					VPTConstants.RESTART_WORKFLOW)) {
				isRestart = true;
			}
		}
		
		/*//Validation of L&T site number
		if(form.getVendrType() != null && form.getSiteNum() != null && (form.getVendrType().trim().equals(VPTConstants.CANADIAN_VENDOR_TYPE_LT_CONSIGNMENT) 
                                || form.getVendrType().trim().equals(VPTConstants.CANADIAN_VENDOR_TYPE_LT_REGULAR))){
			String siteNu = form.getSiteNum();
			if(!siteNu.substring(0).equalsIgnoreCase("2")){
				errors.rejectValue("siteNum","validation.LT.should.start.with.2",VALIDATION_VALID_SITE_START);
			}
		}*/
		
		// VC fields
		if (statusId.equalsIgnoreCase("2180")
				|| statusId.equalsIgnoreCase("2220")) {
			if (!isRework && !isRestart) {
				// if (!isRework
				// &&
				// form.getUserDTO().getUserRoleName().trim().equalsIgnoreCase(
				// VPTConstants.VENDOR_CONTROL)) {
				if ((StringUtils.isBlank(form.getMasterSuplyNum()))) {
					errors.rejectValue("masterSuplyNum",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}
				if ((StringUtils.isBlank(form.getSiteNum()))) {
					errors.rejectValue("siteNum", "validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				} else if ((!StringUtils.isNumeric(form.getSiteNum()) || (form
								.getSiteNum() != null && Integer.parseInt(form
								.getSiteNum().trim()) == 0))) {					
					errors.rejectValue("siteNum", "typeMismatch.java.lang.Long",
							VALIDATION_VALID_NUMBER);
                }  else if (form.getVendrCategory() != null
                        && form.getVendrCategory().trim().equals(VPTConstants.DOMESTIC_VENDOR_CD)
                        && form.getVendrType() != null
                        && (form.getVendrType().trim().equals(VPTConstants.CANADIAN_VENDOR_TYPE_LT_CONSIGNMENT) 
                                || form.getVendrType().trim().equals(VPTConstants.CANADIAN_VENDOR_TYPE_LT_REGULAR)) 
                                && form.getSiteNum().length() != 9) {
                    errors.rejectValue("siteNum", "validation.cannot.be.lessthan.mimimum",
                            VALIDATION_VALID_SITE_NINE_DIGITS_NUMBER);
                } else if (form.getVendrType() != null
                        && !form.getVendrType().trim().equals(VPTConstants.CANADIAN_VENDOR_TYPE_LT_CONSIGNMENT)
                        && !form.getVendrType().trim().equals(VPTConstants.CANADIAN_VENDOR_TYPE_LT_REGULAR)
                        && form.getSiteNum().length() != 6) {
                    errors.rejectValue("siteNum", "validation.cannot.be.lessthan.mimimum",
                            VALIDATION_VALID_SITE_SIX_DIGITS_NUMBER);
                }
                else if(form.getVendrType() != null && form.getSiteNum() != null && (form.getVendrType().trim().equals(VPTConstants.CANADIAN_VENDOR_TYPE_LT_CONSIGNMENT) 
                        || form.getVendrType().trim().equals(VPTConstants.CANADIAN_VENDOR_TYPE_LT_REGULAR))){
							String siteNu = form.getSiteNum();
							if(!siteNu.startsWith("2")){
								errors.rejectValue("siteNum","validation.LT.should.start.with.2",VALIDATION_VALID_SITE_START);
							}
                }
				
				if (form.getStatus().equalsIgnoreCase(
						VPTConstants.PENDING_VC_CLEARANCE)) {
					
					if(form.getVendrCategory() != null
							&& form.getVendrCategory().trim().equals("3400")
							&& form.getVendrType()!=null 
							&& (!form.getVendrType().trim().equals("3460") && !form.getVendrType().trim().equals("3455") 
							&&	!form.getVendrType().trim().equals("3452") && !form.getVendrType().trim().equals("3456") 
							&& !form.getVendrType().trim().equals(VPTConstants.CANADIAN_VENDOR_TYPE_LT_CONSIGNMENT) 
							&& !form.getVendrType().trim().equals(VPTConstants.CANADIAN_VENDOR_TYPE_LT_REGULAR))){
					
						if (form.getVendrCategory() != null
								&& form.getVendrCategory().trim().equals("3400")
								&& (form.getTagMmva() == null
										|| form.getTagMmva().equals("0") 
										|| form.getTagMmva().trim().equals(""))) {
							errors.rejectValue("tagMmva", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
						}
						if ((form.getTagMmva() != null
								&& form.getTagMmva().trim().equals("1") 
								&& (form.getMmvaNum() == null || form.getMmvaNum().trim().equals(VPTConstants.EMPTYSTR)))) {
							errors.rejectValue("mmvaNum", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
						}
						if (!StringUtils.isBlank(form.getMmvaNum())
								&& !StringUtils.isNumeric(form.getMmvaNum())) {
							errors.rejectValue("mmvaNum", "validation.cannot.be.alphabets", VALIDATION_VALID_NUMBER);
						}
					}
					
				}
			}
			if (!StringUtils.isBlank(form.getMasterSuplyNum())
					&& (!StringUtils.isNumeric(form.getMasterSuplyNum()) || (form
							.getMasterSuplyNum() != null && Integer
							.parseInt(form.getMasterSuplyNum().trim()) == 0))) {

				errors.rejectValue("masterSuplyNum",
						"typeMismatch.java.lang.Long", VALIDATION_VALID_NUMBER);
			}
		}

		if (!isRework
				&& form.getUserDTO().getUserRoleName().trim().equalsIgnoreCase(
						VPTConstants.TRANSPORTATION)) {
			if (form.getFobPoint() != null
					&& form.getFobPoint().trim().equals("-1")) {
				errors.rejectValue("fobPoint", "validation.cannot.be.blank",
						VALIDATION_MANDATORY_FIELD);
			}
			if (form.getFreightPayment() != null
					&& form.getFreightPayment().trim().equals("-1")) {
				errors.rejectValue("freightPayment",
						"validation.cannot.be.blank",
						VALIDATION_MANDATORY_FIELD);
			}
		}

		if (!isRework
				&& form.getUserDTO().getUserRoleName().trim().equalsIgnoreCase(
						VPTConstants.OPS_PLANNING)) {
			if (form.getPackedIn() != null
					&& form.getPackedIn().trim().equals("-1")) {
				errors.rejectValue("packedIn", "validation.cannot.be.blank",
						VALIDATION_MANDATORY_FIELD);
			}
		}

		boolean isOriginator = false;
		boolean isVendorAdmin = false;
		boolean isSuperUser = false;

		if (form.getUserDTO().getUserRoleName().trim().equalsIgnoreCase(
				VPTConstants.SUPER_USER)) {
			isSuperUser = true;
		}

		if (statusId.equalsIgnoreCase("2100")
				|| statusId.equalsIgnoreCase("2110") || isRestart || isSuperUser) {
			isOriginator = true;
		}
		if (!isRestart
				&& statusId.equalsIgnoreCase("2120")
				&& (form.getUserDTO().getUserRoleName().trim()
						.equalsIgnoreCase(VPTConstants.VENDOR_ADMIN) || isSuperUser )) {
			isVendorAdmin = true;
		}

		if (isOriginator || isVendorAdmin) {
			if (!isRework) {
				if (StringUtils.isBlank(form.getSupplierName())) {
					errors.rejectValue("supplierName",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}else{
					
					if (StringUtils.isAlphanumericSpace(form.getSupplierName())) {
						if(StringUtils.isNumeric(form.getSupplierName())){
							
							errors.rejectValue("supplierName",
									"validation.cannot.be.numbers",
									VALIDATION_ONLY_ALPHANUMERIC_ALLOWED);
						}
					}
				}
				 
				
				if (StringUtils.isBlank(form.getAltSiteName())) {
					errors.rejectValue("altSiteName",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}
				else{
					
					if (StringUtils.isAlphanumericSpace(form.getAltSiteName())) {
						if(StringUtils.isNumeric(form.getAltSiteName())){
							errors.rejectValue("altSiteName",
									"validation.cannot.be.numbers",
									VALIDATION_ONLY_ALPHANUMERIC_ALLOWED);
						}
					}
				}
				
				if (form.getPaymentCurrency() != null
						&& form.getPaymentCurrency().trim().equals("-1")) {
					errors.rejectValue("paymentCurrency",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}
				if (form.getBrandType() != null){
					String[] brands = form.getBrandType();
						if(brands.length == 0){
							errors.rejectValue("brandType",
									"validation.cannot.be.blank",
									VALIDATION_MANDATORY_FIELD);
						}
				}

				if (form.getPaymentMethod() != null
						&& form.getPaymentMethod().trim().equals("-1")) {
					errors.rejectValue("paymentMethod",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}
				if (form.getPaymentTerms() != null
						&& form.getPaymentTerms().trim().equals("-1")) {
					errors.rejectValue("paymentTerms",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}
				if (StringUtils.isBlank(form.getVendrlanguage())) {
					errors.rejectValue("vendrlanguage",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}
				if (StringUtils.isBlank(form.getVendrDepartment())) {
					errors.rejectValue("vendrDepartment",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}
				if (StringUtils.isBlank(form.getVendrGroup())) {
					errors.rejectValue("vendrGroup",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}
				if (StringUtils.isBlank(form.getVndrCategory())) {
					errors.rejectValue("vndrCategory",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}
				if (StringUtils.isBlank(form.getVendrFirstName())) {
					errors.rejectValue("vendrFirstName",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}
				if (StringUtils.isBlank(form.getVendrEmail().trim())) {
					errors.rejectValue("vendrEmail",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}
				if (StringUtils.isBlank(form.getVendrPhone())) {
					errors.rejectValue("vendrPhone",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}
				if ((StringUtils.isBlank(form.getVendrLastName()))) {
					errors.rejectValue("vendrLastName",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}
				if (form.getBanner() != null && (form.getBanner().length <= 0)) {
					errors.rejectValue("banner", "validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}
			}
               String intialAmt = FormatUtil.replaceCommas(form.getInitOrderAmnt());
			if (!StringUtils.isBlank(intialAmt)
					&& !isValidAmount(intialAmt)) {
				errors.rejectValue("initOrderAmnt",
						"typeMismatch.java.lang.Long", VALIDATION_VALID_NUMBER);
			}
			/////////////E136/////////////////
			/*if (!StringUtils.isBlank(form.getOrderMinQty())
					&& (!StringUtils.isNumeric(form.getOrderMinQty()))) {
				errors.rejectValue("orderMinQty",
						"typeMismatch.java.lang.Long", VALIDATION_VALID_NUMBER);
			}*/
			/////////////E136/////////////////
			 String orderMinAmt = FormatUtil.replaceCommas(form.getOrderMinVal());
			if (!StringUtils.isBlank(orderMinAmt)
					&& !isValidAmount(orderMinAmt)) {
				errors.rejectValue("orderMinVal",
						"typeMismatch.java.lang.Long", VALIDATION_VALID_NUMBER);
			}
			 String estmAnnumPOValToVendr = FormatUtil.replaceCommas(form.getEstmAnnumPOValToVendr());
			if (!(StringUtils.isBlank(estmAnnumPOValToVendr))
					&& !isValidAmount(estmAnnumPOValToVendr)) {
				errors.rejectValue("estmAnnumPOValToVendr",
						"typeMismatchPO.java.lang.Long",
						"Please enter numbers with two decimals");
			}
			if (!StringUtils.isBlank(form.getEstmAnnumSaleCartons())
					&& (!StringUtils.isNumeric(form.getEstmAnnumSaleCartons()))) {
				errors.rejectValue("estmAnnumSaleCartons",
						"typeMismatch.java.lang.Long", VALIDATION_VALID_NUMBER);
			}
			if (!StringUtils.isBlank(form.getEstmAnnumSaleUnits())
					&& (!StringUtils.isNumeric(form.getEstmAnnumSaleUnits()))) {
				errors.rejectValue("estmAnnumSaleUnits",
						"typeMismatch.java.lang.Long", VALIDATION_VALID_NUMBER);
			}

			if (!StringUtils.isBlank(form.getEstmCartonSZinLen())
					&& (!StringUtils.isNumeric(form.getEstmCartonSZinLen()))) {
				errors.rejectValue("estmCartonSZinLen",
						"typeMismatch.java.lang.Long", VALIDATION_VALID_NUMBER);
			} else if (!StringUtils.isBlank(form.getEstmCartonSZinWidth())
					&& (!StringUtils.isNumeric(form.getEstmCartonSZinWidth()))) {
				errors.rejectValue("estmCartonSZinWidth",
						"typeMismatch.java.lang.Long", VALIDATION_VALID_NUMBER);
			} else if (!StringUtils.isBlank(form.getEstmCartonSZinHight())
					&& (!StringUtils.isNumeric(form.getEstmCartonSZinHight()))) {
				errors.rejectValue("estmCartonSZinHight",
						"typeMismatch.java.lang.Long", VALIDATION_VALID_NUMBER);
			}
			if (!StringUtils.isBlank(form.getEstmAnnumShipsNum())
					&& (!StringUtils.isNumeric(form.getEstmAnnumShipsNum()))) {
				errors.rejectValue("estmAnnumShipsNum",
						"typeMismatch.java.lang.Long", VALIDATION_VALID_NUMBER);
			}

			if (!StringUtils.isBlank(form.getVendrFirstName())
					&& !StringUtils.isAlphaSpace(form.getVendrFirstName())) {
				errors.rejectValue("vendrFirstName",
						"validation.cannot.be.numbers", VALIDATION_ALPHABETS);
			}
			if (!isValidEmailAddress(form.getVendrEmail().trim())
					&& StringUtils.isNotBlank(form.getVendrEmail().trim())) {
				errors.rejectValue("vendrEmail",
						"validation.not.valid.email.id",
						"Please enter a valid Email Address");

			}
			if (!StringUtils.isBlank(form.getVendrPhone())) {
				if (!StringUtils.containsOnly(form.getVendrPhone(),
						VPTConstants.phoneArray)) {
					errors.rejectValue("vendrPhone",
							"validation.cannot.be.alphabets",
							VALIDATION_VALID_NUMBER_DASH);
				} else if (checkHash(form.getVendrPhone())) {
					errors.rejectValue("vendrPhone",
							"validation.cannot.be.alphabets",
							VALIDATION_VALID_NUMBER_DASH);
				}
			}

			if (!StringUtils.isBlank(form.getVendrLastName())
					&& !StringUtils
							.isAlphaSpace(form.getVendrLastName().trim())) {
				errors.rejectValue("vendrLastName",
						"validation.cannot.be.alphabets", VALIDATION_ALPHABETS);
			}

			if (!StringUtils.isBlank(form.getEdiContactName())
					&& !StringUtils.isAlphaSpace(form.getEdiContactName())) {
				errors.rejectValue("ediContactName",
						"validation.cannot.be.alphabets", VALIDATION_ALPHABETS);
			}
			if (!StringUtils.isBlank(form.getEdiPhone())
					&& !StringUtils.containsOnly(form.getEdiPhone(),
							VPTConstants.phoneArray)) {
				errors.rejectValue("ediPhone",
						"validation.cannot.be.alphabets",
						VALIDATION_VALID_NUMBER_DASH);
			}
			if (!isValidEmailAddress(form.getEdiEmailId())
					&& StringUtils.isNotBlank(form.getEdiEmailId())) {
				errors.rejectValue("ediEmailId",
						"validation.not.valid.email.id",
						"Please enter a valid Email Address");
			}
			
		//E138	
			
			if (StringUtils.isBlank(form.getOrderRetrnLeadTime())) {
				errors.rejectValue("orderRetrnLeadTime",
						"validation.cannot.be.blank",
						VALIDATION_MANDATORY_FIELD);
			}
// E164 changes Start
			
			if (!StringUtils.isBlank(form.getEdiPvdContactName())
					&& !StringUtils.isAlphaSpace(form.getEdiPvdContactName())) {
				errors.rejectValue("ediPvdContactName",
						"validation.cannot.be.alphabets", VALIDATION_ALPHABETS);
			}
			if (!StringUtils.isBlank(form.getEdiPvdPhone())
					&& !StringUtils.containsOnly(form.getEdiPvdPhone(),
							VPTConstants.phoneArray)) {
				errors.rejectValue("ediPvdPhone",
						"validation.cannot.be.alphabets",
						VALIDATION_VALID_NUMBER_DASH);
			}
			if (!isValidEmailAddress(form.getEdiPvdEmailId())
					&& StringUtils.isNotBlank(form.getEdiPvdEmailId())) {
				errors.rejectValue("ediPvdEmailId",
						"validation.not.valid.email.id",
						"Please enter a valid Email Address");
			}
			
			
//E164 changes End			
			
			if (!StringUtils.isBlank(form.getDunsNum())
					&& !StringUtils.isNumeric(form.getDunsNum())) {
				errors.rejectValue("dunsNum", "validation.cannot.be.alphabets",
						VALIDATION_VALID_NUMBER);
			}

			/* Only for Originator */
			if (isOriginator) {

				if (StringUtils.isBlank(form.getOriginatorPhone())) {
					errors.rejectValue("originatorPhone",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				} else if (!StringUtils.containsOnly(form.getOriginatorPhone(),
						VPTConstants.phoneArray)) {
					errors.rejectValue("originatorPhone",
							"validation.cannot.be.alphabets",
							VALIDATION_VALID_NUMBER_DASH);
				} else if (checkHash(form.getOriginatorPhone())) {
					errors.rejectValue("originatorPhone",
							"validation.cannot.be.alphabets",
							"must be a valid number and '-'");
				}

				if ((StringUtils.isBlank(form.getAltContactId()))) {
					errors.rejectValue("altContactId",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}

				if ((StringUtils.isBlank(form.getAltContactPhone()))) {
					errors.rejectValue("altContactPhone",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				} else if (!StringUtils.containsOnly(form.getAltContactPhone(),
						VPTConstants.phoneArray)) {
					errors.rejectValue("altContactPhone",
							"validation.cannot.be.alphabets",
							"must be a valid number and '-'");
				} else if (checkHash(form.getAltContactPhone())) {
					errors.rejectValue("altContactPhone",
							"validation.cannot.be.alphabets",
							"must be a valid number and '-'");
				}

				if ((StringUtils.isBlank(form.getGmmId()))) {
					errors.rejectValue("gmmId", "validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}

				if (StringUtils.isBlank(form.getGmmPhone())) {
					errors.rejectValue("gmmPhone",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				} else if (!StringUtils.containsOnly(form.getGmmPhone(),
						VPTConstants.phoneArray)) {
					errors.rejectValue("gmmPhone",
							"validation.cannot.be.alphabets",
							VALIDATION_VALID_NUMBER_DASH);
				} else if (checkHash(form.getGmmPhone())) {
					errors.rejectValue("gmmPhone",
							"validation.cannot.be.alphabets",
							"must be a valid number and '-'");
				}
                if (form.getVendrType() != null
                        && (form.getVendrType().trim()
                                .equals(VPTConstants.CANADIAN_VENDOR_TYPE_LT_CONSIGNMENT) || form
                                .getVendrType().trim()
                                .equals(VPTConstants.CANADIAN_VENDOR_TYPE_LT_REGULAR))) {
                    if (form.getSvpMerchId() == null || form.getSvpMerchId().trim().length() <= 0) {
                        errors.rejectValue("svpMerchId", "validation.cannot.be.blank",
                                VALIDATION_MANDATORY_FIELD);
                    }
                }

				if (form.getPackedIn() == null) {
					errors.rejectValue("packedIn",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}

				
			/*	if (!form.isPrepackSKUInd() && !form.isCofiInd()
						&& !form.isDirectToStoreInd() && !form.isVmiInd()) {
					errors.rejectValue("prepackSKUInd",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}
			*/

				if (form.getFobPoint() != null
						&& form.getFobPoint().trim().equals("-1")) {
					errors.rejectValue("fobPoint",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}

				if (form.getFreightPayment() != null
						&& form.getFreightPayment().trim().equals("-1")) {
					errors.rejectValue("freightPayment",
							"validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}
			}

			/* Only for vendor admin */
			if ((isVendorAdmin) && !isRework) {
//				if (StringUtils.isBlank(form.getOrderRetrnLeadTime())) {
//					errors.rejectValue("orderRetrnLeadTime",
//							"validation.cannot.be.blank",
//							VALIDATION_MANDATORY_FIELD);
//				}

				if(form.isOverseasVendor() == false && !(form.getVendrType().equalsIgnoreCase("3459"))){
					if (StringUtils.isBlank(form.getGstNum())) {
						errors.rejectValue("gstNum", "validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
					}
				}

				List<AddressForm> lstAdrsForm = form.getLstAddressForm();
				if (lstAdrsForm != null && lstAdrsForm.size() > 0) {
					for (int i = 0; i < lstAdrsForm.size(); i++) {
						AddressForm adrsForm = lstAdrsForm.get(i);
						if (adrsForm.getAddressTypeId() != null
								&& !adrsForm.getAddressTypeId().equals(
										VPTConstants.EMPTYSTR)
								&& !adrsForm.getAddressTypeId().equals(
										VPTConstants.ZERO)) {
							String tempEmail = adrsForm.getEmailId();
							if (tempEmail == null 
									|| StringUtils.isBlank(tempEmail.trim())
									|| StringUtils.isBlank(adrsForm
											.getFirstName())
									|| StringUtils.isBlank(adrsForm
											.getLastName())
									|| StringUtils.isBlank(adrsForm
											.getAddress1())
									|| StringUtils.isBlank(adrsForm.getCity())
									|| StringUtils.isBlank(adrsForm.getState())
									|| StringUtils.isBlank(adrsForm
											.getCountry())
									|| StringUtils.isBlank(adrsForm.getZip())
									|| StringUtils.isBlank(adrsForm.getPhone())) {
								errors.rejectValue("lstAddressForm[" + i + "]",
										"validation.cannot.be.blank",
										VALIDATION_MANDATORY_FIELD);
							}
						}
					}

				}
			}
		}
		LOGGER.debug("errors::" + errors.getAllErrors());

	}

	/**
	 * Method Name : validatePopUp
	 * 
	 * Description : This Method is used for validation for address pop up..
	 * 
	 * @param : AddressForm form
	 * 
	 * @param : Errors errors
	 */
	public void validatePopUp(AddressForm form, Errors errors) {
		LOGGER.debug("================ validate ADDRESS (SMF) =========");

		if ((StringUtils.isBlank(form.getFirstName()))) {
			errors.rejectValue("firstName", "validation.cannot.be.blank",
					VALIDATION_MANDATORY_FIELD);
		} else if (!StringUtils.isAlphaSpace(form.getFirstName())) {
			errors.rejectValue("firstName", "validation.cannot.be.alphabets",
					VALIDATION_ALPHABETS);
		}

		if ((StringUtils.isBlank(form.getLastName()))) {
			errors.rejectValue("lastName", "validation.cannot.be.blank",
					VALIDATION_MANDATORY_FIELD);
		} else if (!StringUtils.isAlphaSpace(form.getLastName())) {
			errors.rejectValue("lastName", "validation.cannot.be.alphabets",
					VALIDATION_ALPHABETS);
		}

		if ((StringUtils.isBlank(form.getAddress1()))) {
			errors.rejectValue("address1", "validation.cannot.be.blank",
					VALIDATION_MANDATORY_FIELD);
		}

		if ((StringUtils.isBlank(form.getCity()))) {
			errors.rejectValue("city", "validation.cannot.be.blank",
					VALIDATION_MANDATORY_FIELD);
		}

		if ((StringUtils.isBlank(form.getState()))) {
			errors.rejectValue("state", "validation.cannot.be.blank",
					VALIDATION_MANDATORY_FIELD);
		}

		if ((StringUtils.isBlank(form.getCountry()))) {
			errors.rejectValue("country", "validation.cannot.be.blank",
					VALIDATION_MANDATORY_FIELD);
		}

		if ((StringUtils.isBlank(form.getZip()))) {
			errors.rejectValue("zip", "validation.cannot.be.blank",
					VALIDATION_MANDATORY_FIELD);
		}
		if ((StringUtils.isBlank(form.getEmailId()))) {
	            errors.rejectValue("emailId", "validation.cannot.be.blank",
	                    VALIDATION_MANDATORY_FIELD);
	    }else if (!isValidEmailAddress(form.getEmailId())) {
			errors.rejectValue("emailId", "validation.not.valid.email.id",
					"Please enter a valid Email Address");

		}
		if ((StringUtils.isBlank(form.getPhone()))) {
			errors.rejectValue("phone", "validation.cannot.be.blank",
					VALIDATION_MANDATORY_FIELD);
		} else if (!StringUtils.containsOnly(form.getPhone(),
				VPTConstants.phoneArray)) {
			errors.rejectValue("phone", "validation.not.valid.phone",
					"must be a valid number and  '-' ");
		} else if (checkHash(form.getPhone())) {
			errors.rejectValue("Phone", "validation.cannot.be.alphabets",
					VALIDATION_VALID_NUMBER_DASH);
		}

		if (!StringUtils.isNumeric(form.getFaxNumber())) {
			errors.rejectValue("faxNumber", "validation.not.valid.email.id",
					VALIDATION_VALID_NUMBER);
		}

		if (!StringUtils.isBlank(form.getOwnerName())
				&& !StringUtils.isAlphaSpace(form.getOwnerName())) {
			errors.rejectValue("ownerName", "validation.cannot.be.alphabets",
					VALIDATION_ALPHABETS);
		}
		if (!StringUtils.isBlank(form.getManagerName())
				&& !StringUtils.isAlphaSpace(form.getManagerName())) {
			errors.rejectValue("managerName", "validation.cannot.be.alphabets",
					VALIDATION_ALPHABETS);
		}
		if (!StringUtils.isBlank(form.getShipFrmContactName())
				&& !StringUtils.isAlphaSpace(form.getShipFrmContactName())) {
			errors.rejectValue("shipFrmContactName",
					"validation.cannot.be.alphabets", VALIDATION_ALPHABETS);
		}

		if (!StringUtils.isBlank(form.getOwnerPhone())
				&& !StringUtils.containsOnly(form.getOwnerPhone(),
						VPTConstants.phoneArray)) {
			errors.rejectValue("ownerPhone", "validation.cannot.be.alphabets",
					VALIDATION_VALID_NUMBER_DASH);
		}
		if (!StringUtils.isBlank(form.getManagerPhone())
				&& !StringUtils.containsOnly(form.getManagerPhone(),
						VPTConstants.phoneArray)) {
			errors.rejectValue("managerPhone",
					"validation.cannot.be.alphabets",
					VALIDATION_VALID_NUMBER_DASH);
		}
		if (!StringUtils.isBlank(form.getShipFrmContactPhone())
				&& !StringUtils.containsOnly(form.getShipFrmContactPhone(),
						VPTConstants.phoneArray)) {
			errors.rejectValue("shipFrmContactPhone",
					"validation.cannot.be.alphabets",
					VALIDATION_VALID_NUMBER_DASH);
		}
		if (!isValidEmailAddress(form.getOwnerEmail())
				&& StringUtils.isNotBlank(form.getOwnerEmail())) {
			errors.rejectValue("ownerEmail", "validation.not.valid.email.id",
					"Please enter a valid Email Address");
		}
		
		if (!isValidEmailAddress(form.getManagerEmail())
				&& StringUtils.isNotBlank(form.getManagerEmail())) {
			errors.rejectValue("managerEmail", "validation.not.valid.email.id",
					"Please enter a valid Email Address");
		}
		if (!isValidEmailAddress(form.getShipFrmContactEmail())
				&& StringUtils.isNotBlank(form.getShipFrmContactEmail())) {
			errors.rejectValue("shipFrmContactEmail",
					"validation.not.valid.email.id",
					"Please enter a valid Email Address");
		}
		LOGGER.debug("errors::" + errors.getAllErrors());
	}

	/**
	 * Method Name : validatePopUp
	 * 
	 * Description : This Method is used for validation for expense vendor..
	 * 
	 * @param : ExpenseVendorForm form
	 * 
	 * @param : Errors errors
	 */
	public static void checkExpenseVendorForm(ExpenseVendorForm form,
			Errors errors) throws VPApplicationException {
		LOGGER.debug("================validate EXPENSE VENDOR=========");

		boolean isRework = false;
		boolean isRestart = false;
		//EDI validation is only for HBC and not for L&T - 3497 is L&T Regular
		if(!form.getStatusId().equals("2100") && !form.getStatusId().equals("2110")){
			if(!form.getVendrType().equals("3497")){
					if(form.getEdiContactName() == null || form.getEdiContactName().trim().equals("")){
						errors.rejectValue("ediContactName", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
					}
					if (form.getEdiTitle() == null || form.getEdiTitle().trim().equals("")) {
						errors.rejectValue("ediTitle", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
					}
					if(form.getEdiPhone() == null || form.getEdiPhone().trim().equals("")){
						errors.rejectValue("ediPhone", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
					}
					if(form.getEdiEmailId() == null || form.getEdiEmailId().trim().equals("")){
						errors.rejectValue("ediEmailId", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
					}
					if(form.getEdiFax() == null || form.getEdiFax().trim().equals("")){
						errors.rejectValue("ediFax", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
					}
					if(form.getEdiProviderType().equals("-1")){
						errors.rejectValue("ediProviderType", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
					}
					if(form.getEdiProviderType().equals("3501") && (form.getEdiProvider() == null || form.getEdiProvider().trim().equals(""))){
						errors.rejectValue("ediProvider", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
					}
					if(form.getEdiProviderType().equals("3501") && (form.getEdiPvdContactName() == null || form.getEdiPvdContactName().trim().equals(""))){
						errors.rejectValue("ediPvdContactName", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
					}
					if(form.getEdiProviderType().equals("3501") && (form.getEdiPvdTitle() == null || form.getEdiPvdTitle().trim().equals(""))){
						errors.rejectValue("ediPvdTitle", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
					}
					if(form.getEdiProviderType().equals("3501") && (form.getEdiPvdPhone() == null || form.getEdiPvdPhone().trim().equals(""))){
						errors.rejectValue("ediPvdPhone", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
					}
					if(form.getEdiProviderType().equals("3501") && (form.getEdiPvdEmailId() == null || form.getEdiPvdEmailId().trim().equals(""))){
						errors.rejectValue("ediPvdEmailId", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
					}
					if(form.getEdiProviderType().equals("3501")&& (form.getEdiPvdFax() == null || form.getEdiPvdFax().trim().equals(""))){
						errors.rejectValue("ediPvdFax", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
					}
					String[] productionAttrib = form.getEdiTest();
					if(productionAttrib[0] == null || productionAttrib[0].trim().equals("")){
						errors.rejectValue("ediTest[0]", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
					}
					if(productionAttrib[1] == null || productionAttrib[1].trim().equals("")){
						errors.rejectValue("ediTest[1]", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
					}
					if(productionAttrib[2] == null || productionAttrib[2].trim().equals("")){
						errors.rejectValue("ediTest[2]", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
					}
					if(productionAttrib[3] == null || productionAttrib[3].trim().equals("")){
						errors.rejectValue("ediTest[3]", "validation.cannot.be.blank", VALIDATION_MANDATORY_FIELD);
					}
			}
		}
		
		if(form.getVendrType() != null && form.getSiteNum() != null && form.getVendrType().trim().equals(VPTConstants.EXPENSE_VENDOR_TYPE_LT_REGULAR)){
			String siteNu = form.getSiteNum();
						
			if(!siteNu.startsWith("2")){
				errors.rejectValue("siteNum","validation.LT.should.start.with.2",VALIDATION_VALID_SITE_START);
			}
			else if(form.getSiteNum().trim().length() != 9){
				errors.rejectValue("siteNum","validation.must.contain.maximun.length",VALIDATION_VALID_SITE_NINE_DIGITS_NUMBER);
			}
		}
		
		if (form.getActionName() != null) {
			if (form.getActionName().equalsIgnoreCase(VPTConstants.REWORK)) {
				isRework = true;
			}
			if (form.getActionName().equalsIgnoreCase(
					VPTConstants.RESTART_WORKFLOW)) {
				isRestart = true;
			}
		}
		String statusId = form.getStatusId();

		if (statusId == null)
			throw new VPApplicationException("Status Id cannot be null");

		statusId = statusId.trim();

		if (StringUtils.isBlank(form.getOriginatorPhone())) {
			errors.rejectValue("originatorPhone", "validation.cannot.be.blank",
					VALIDATION_MANDATORY_FIELD);
		} else if (!StringUtils.containsOnly(form.getOriginatorPhone(),
				VPTConstants.phoneArray)) {
			errors.rejectValue("originatorPhone",
					"validation.cannot.be.alphabets",
					VALIDATION_VALID_NUMBER_DASH);
		}

		if (StringUtils.isBlank(form.getSupplierName())) {
			errors.rejectValue("supplierName", "validation.cannot.be.blank",
					VALIDATION_MANDATORY_FIELD);
		}
		else{
			if (StringUtils.isAlphanumericSpace(form.getSupplierName())) {
				if(StringUtils.isNumeric(form.getSupplierName())){
					
					errors.rejectValue("supplierName",
							"validation.cannot.be.numbers",
							VALIDATION_ONLY_ALPHANUMERIC_ALLOWED);
				}
			}
		}

		if (StringUtils.isBlank(form.getAltSiteName())) {
			errors.rejectValue("altSiteName", "validation.cannot.be.blank",
					VALIDATION_MANDATORY_FIELD);
		}
		else{
			
			if (StringUtils.isAlphanumericSpace(form.getAltSiteName())) {
				if(StringUtils.isNumeric(form.getAltSiteName())){
					errors.rejectValue("altSiteName",
							"validation.cannot.be.numbers",
							VALIDATION_ONLY_ALPHANUMERIC_ALLOWED);
				}
			}
		}
		
		if (StringUtils.isBlank(form.getGmmId())) {
			errors.rejectValue("gmmId", "validation.cannot.be.blank",
					VALIDATION_MANDATORY_FIELD);
		}
		
		if (StringUtils.isBlank(form.getGmmPhone())) {
			errors.rejectValue("gmmPhone", "validation.cannot.be.blank",
					VALIDATION_MANDATORY_FIELD);
		} else if (!StringUtils.containsOnly(form.getGmmPhone(),
				VPTConstants.phoneArray)) {
			errors.rejectValue("gmmPhone",
					"validation.cannot.be.alphabets",
					VALIDATION_VALID_NUMBER_DASH);
		}
		
		if (!isRework && (StringUtils.isBlank(form.getVendrFirstName()))) {
			errors.rejectValue("vendrFirstName", "validation.cannot.be.blank",
					VALIDATION_MANDATORY_FIELD);
		} else if (!StringUtils.isAlphaSpace(form.getVendrFirstName())) {
			errors.rejectValue("vendrFirstName",
					"validation.cannot.be.numbers",
					VALIDATION_ONLY_ALPHABETS_ALLOWED);

		}
		if ((StringUtils.isBlank(form.getVendrEmail().trim()))) {
            errors.rejectValue("vendrEmail", "validation.cannot.be.blank",
                    VALIDATION_MANDATORY_FIELD);
		}else if (!isValidEmailAddress(form.getVendrEmail().trim())) {
			errors.rejectValue("vendrEmail", "validation.not.valid.email.id",
					VALIDATION_EMAIL_ID);
		}
		if ((StringUtils.isBlank(form.getVendrPhone()))) {
			errors.rejectValue("vendrPhone", "validation.cannot.be.blank",
					VALIDATION_MANDATORY_FIELD);
		} else if (!StringUtils.containsOnly(form.getVendrPhone(),
				VPTConstants.phoneArray)) {
			errors.rejectValue("vendrPhone", "validation.cannot.be.alphabets",
					"Should be numeric, may contain '-'");
		}
		if (!isRework && (StringUtils.isBlank(form.getVendrLastName()))) {
			errors.rejectValue("vendrLastName", "validation.cannot.be.blank",
					VALIDATION_MANDATORY_FIELD);
		} else if (!StringUtils.isAlphaSpace(form.getVendrLastName())) {
			errors.rejectValue("vendrLastName",
					"validation.cannot.be.alphabets",
					VALIDATION_ONLY_ALPHABETS_ALLOWED);
		}
		if (!StringUtils.isBlank(form.getEdiContactName())
				&& !StringUtils.isAlphaSpace(form.getEdiContactName())) {
			errors.rejectValue("ediContactName",
					"validation.cannot.be.alphabets",
					VALIDATION_ONLY_ALPHABETS_ALLOWED);
		}
		if (!StringUtils.isBlank(form.getEdiPhone())
				&& !StringUtils.containsOnly(form.getEdiPhone(),
						VPTConstants.phoneArray)) {
			errors.rejectValue("ediPhone", "validation.cannot.be.alphabets",
					"Should be numeric, may contain '-'");
		}
		if (StringUtils.isNotBlank(form.getEdiEmailId()) 
				&& !isValidEmailAddress(form.getEdiEmailId())) {
			errors.rejectValue("ediEmailId", "validation.not.valid.email.id",
					VALIDATION_EMAIL_ID);
		}
		//E164 changes start
		
		if (!StringUtils.isBlank(form.getEdiPvdContactName())
				&& !StringUtils.isAlphaSpace(form.getEdiPvdContactName())) {
			errors.rejectValue("ediContactName",
					"validation.cannot.be.alphabets",
					VALIDATION_ONLY_ALPHABETS_ALLOWED);
		}
		//TODO
		if (!StringUtils.isBlank(form.getEdiPvdPhone())
				&& !StringUtils.containsOnly(form.getEdiPvdPhone(),
						VPTConstants.phoneArray)) {
			errors.rejectValue("ediPvdPhone", "validation.cannot.be.alphabets",
					"Should be numeric, may contain '-'");
		}
		if (StringUtils.isNotBlank(form.getEdiPvdEmailId()) 
				&& !isValidEmailAddress(form.getEdiPvdEmailId())) {
			errors.rejectValue("ediPvdEmailId", "validation.not.valid.email.id",
					VALIDATION_EMAIL_ID);
		}
		//E164 -end
		if (!isRework
				&& form.getStatus().equalsIgnoreCase(
						VPTConstants.PENDING_VC_APPROVAL)
				&& (form.getUserDTO().getUserRoleName().trim().equalsIgnoreCase(
						VPTConstants.VENDOR_CONTROL) ||
						form.getUserDTO().getUserRoleName().trim().equalsIgnoreCase(
								VPTConstants.SUPER_USER))) {

			if ((StringUtils.isBlank(form.getSupplierNumber()))) {
				errors.rejectValue("supplierNumber",
						"validation.cannot.be.blank",
						VALIDATION_MANDATORY_FIELD);
			}
			if ((StringUtils.isBlank(form.getSiteNum()))) {
				errors.rejectValue("siteNum", "validation.cannot.be.blank",
						VALIDATION_MANDATORY_FIELD);
			}
		}
		if (form.getStatus().equalsIgnoreCase(VPTConstants.PENDING_VC_APPROVAL)
				&& form.getUserDTO().getUserRoleName().trim().equalsIgnoreCase(
						VPTConstants.VENDOR_CONTROL)) {

			if (!StringUtils.isBlank(form.getSupplierNumber())
					&& (!StringUtils.isNumeric(form.getSupplierNumber()) || (form
							.getSupplierNumber() != null && Integer
							.parseInt(form.getSupplierNumber().trim()) == 0))) {
				errors.rejectValue("supplierNumber",
						"typeMismatch.java.lang.Long", VALIDATION_VALID_NUMBER);
			}
			if (!StringUtils.isBlank(form.getSiteNum())
					&& (!StringUtils.isNumeric(form.getSiteNum()) || (form
							.getSiteNum() != null && Integer.parseInt(form
							.getSiteNum().trim()) == 0))) {
				errors.rejectValue("siteNum", "typeMismatch.java.lang.Long",
						VALIDATION_VALID_NUMBER);
			}
		}
		if (statusId.equalsIgnoreCase("2100")
				|| statusId.equalsIgnoreCase("2110") || isRestart) {
			if (form.getPaymentCurrency() != null
					&& form.getPaymentCurrency().trim().equals("-1")) {
				errors.rejectValue("paymentCurrency",
						"validation.cannot.be.blank",
						VALIDATION_MANDATORY_FIELD);
			}

			if (form.getInvoiceCurrency() != null
					&& form.getInvoiceCurrency().trim().equals("-1")) {
				errors.rejectValue("invoiceCurrency",
						"validation.cannot.be.blank",
						VALIDATION_MANDATORY_FIELD);
			}
			
			if (form.getPaymentMethod() != null
					&& form.getPaymentMethod().trim().equals("-1")) {
				errors.rejectValue("paymentMethod",
						"validation.cannot.be.blank",
						VALIDATION_MANDATORY_FIELD);
			}
			if (form.getPaymentTerms() != null
					&& form.getPaymentTerms().trim().equals("-1")) {
				errors.rejectValue("paymentTerms",
						"validation.cannot.be.blank",
						VALIDATION_MANDATORY_FIELD);
			}
			if (form.getVendrlanguage() != null
					&& form.getVendrlanguage().trim().equals("")) {
				errors.rejectValue("vendrlanguage",
						"validation.cannot.be.blank",
						VALIDATION_MANDATORY_FIELD);
			}
		}
		// Vendor mandatory fields
		if (!isRework
				&& (form.getUserDTO().getUserRoleName().trim().equalsIgnoreCase(
						VPTConstants.VENDOR_ADMIN) || form.getStatusId().equals("2120"))) {

			if(form.isCanadianVendor()){
				if (form.getGstNum() != null && form.getGstNum().trim().equals("")) {
					errors.rejectValue("gstNum", "validation.cannot.be.blank",
							VALIDATION_MANDATORY_FIELD);
				}
			}
			List<AddressForm> lstAdrsForm = form.getLstAddressForm();
			if (lstAdrsForm != null && lstAdrsForm.size() > 0) {
				for (int i = 0; i < lstAdrsForm.size(); i++) {
					AddressForm adrsForm = lstAdrsForm.get(i);
					if (adrsForm.getAddressTypeId() != null
							&& !adrsForm.getAddressTypeId().equals(
									VPTConstants.EMPTYSTR)
							&& !adrsForm.getAddressTypeId().equals(
									VPTConstants.ZERO)) {

						if (adrsForm.getEmailId() == null
								|| adrsForm.getEmailId().trim().equals("")
								|| adrsForm.getFirstName() == null
								|| adrsForm.getFirstName().trim().equals("")
								|| adrsForm.getLastName() == null
								|| adrsForm.getLastName().trim().equals("")
								|| adrsForm.getAddress1() == null
								|| adrsForm.getAddress1().trim().equals("")
								|| adrsForm.getCity() == null
								|| adrsForm.getCity().trim().equals("")
								|| adrsForm.getState() == null
								|| adrsForm.getState().trim().equals("")
								|| adrsForm.getCountry() == null
								|| adrsForm.getCountry().trim().equals("")
								|| adrsForm.getZip() == null
								|| adrsForm.getZip().trim().equals("")
								|| adrsForm.getPhone() == null
								|| adrsForm.getPhone().trim().equals("")) {
							errors.rejectValue("lstAddressForm[" + i + "]",
									"validation.cannot.be.blank",
									VALIDATION_MANDATORY_FIELD);
						}
					}
				}

			}
		}
		LOGGER.debug("errors::" + errors.getAllErrors());
	}

	/**
	 * Method Name : checkHash
	 * 
	 * Description : This is a helper method
	 * 
	 * @param : String value
	 * 
	 * @return : boolean
	 */
	private static boolean checkHash(String value) {
		int length = value.length();
		if (value.contains("--")) {
			return true;
		} else if (value.indexOf("-") == 0) {
			return true;
		} else if (length - 1 == value.lastIndexOf("-")) {
			return true;
		}
		return false;
	}

/********** E111 changes start -email validation(Generic)
*/
	
/*	private static boolean isValidEmailAddress(String email) {
		Pattern emailpattern=Pattern.compile("[a-zA-Z0-9_]+\\.?[a-zA-Z0-9_]+@[a-zA-Z0-9]+[-]?[a-zA-Z0-9]+\\.[a-zA-Z0-9]+\\.?[a-zA-Z0-9]+");
		 							//		"^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		if(email!=null && !email.trim().equals("")){
	    	int index=email.indexOf('@');
	    	LOGGER.debug("Value of index=="+index);
	    	if(index>0){
	    		String str=email.substring(0,index);
		    	index=str.indexOf('.');
		    	if(index==-1){
		    		index=str.indexOf('-');
		    		if(index!=-1){
		    			emailpattern=Pattern.compile("[a-zA-Z0-9_]+[-]?[a-zA-Z0-9_]+@[a-zA-Z0-9]+[-]?[a-zA-Z0-9]+\\.[a-zA-Z0-9]+\\.?[a-zA-Z0-9]+");
		    		}
		    	}else{
		    		String str1=str.substring(0, index);
		    		String str2=str.substring(index);
		    		String reqPattern="[a-zA-Z0-9_]+\\.?[a-zA-Z0-9_]+@[a-zA-Z0-9]+[-]?[a-zA-Z0-9]+\\.[a-zA-Z0-9]+\\.?[a-zA-Z0-9]+";
		    		index=str1.indexOf('-');
		    		if(index!=-1){
		    			//reqPattern=reqPattern.replaceFirst("\\.?[a-zA-Z0-9_]+", "");
		    			reqPattern="[a-zA-Z0-9_]+[-]?[a-zA-Z0-9_]+\\.?[a-zA-Z0-9_]+@[a-zA-Z0-9]+[-]?[a-zA-Z0-9]+\\.[a-zA-Z0-9]+\\.?[a-zA-Z0-9]+";
		    		}
		    		index=str2.indexOf('-');
		    		if(index!=-1){
		    			//reqPattern=reqPattern.replaceFirst("\\.?[a-zA-Z0-9_]+", "");
		    			reqPattern="[a-zA-Z0-9_]+\\.?[a-zA-Z0-9_]+[-]?[a-zA-Z0-9_]+@[a-zA-Z0-9]+[-]?[a-zA-Z0-9]+\\.[a-zA-Z0-9]+\\.?[a-zA-Z0-9]+";
		    		}
		    		if(str1.indexOf('-')>0 && str2.indexOf('-')>0 ){
		    			reqPattern="[a-zA-Z0-9_]+[-]?[a-zA-Z0-9_]+\\.?[a-zA-Z0-9_]+[-]?[a-zA-Z0-9_]+@[a-zA-Z0-9]+[-]?[a-zA-Z0-9]+\\.[a-zA-Z0-9]+\\.?[a-zA-Z0-9]+";
		    		}
//		    		System.out.println("reqPattern="+reqPattern);
		    		emailpattern=Pattern.compile(reqPattern);
		    	}
	    	}
		}
		return (StringUtils.isNotBlank(email)) ? emailpattern.matcher(email)
				.matches() : false;
	}*/

	private static boolean isValidEmailAddress(String email) {
		if(email == null){
			return false;
		}else{
				
			email.trim();
			LOGGER.debug("COMING INSIDE"+email);
		}
		return (StringUtils.isNotBlank(email)) ? EMAIL_PATTERN.matcher(email)
				.matches() : false;
	}
// E111 changes -End	

	private static boolean isValidAmount(String amount) {
		return (StringUtils.isNotBlank(amount)) ? AMOUNT_PATTERN
				.matcher(amount).matches() : false;
	}
}
