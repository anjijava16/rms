package com.iwinner.rms.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON_INFO")
public class PersonalInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PERS_ID")
	private Integer personId;

	@Column(name = "PERS_NAME")
	private Integer personName;

	@Column(name = "PERS_CREDIT_AMOUNT")
	private Float personCreditAmount;

	@Column(name = "PERS_DEBIT_AMOUNT")
	private Float personDreditAmount;

	@Column(name = "PERS_CREDIT_NAME")
	private String creditName;

	@Column(name = "PERS_DEBIT_NAME")
	private String debitName;

	@Column(name = "PERS_CURRENCY_TYPE")
	private CurrencyType currencyType;

	@Column(name = "PERS_TYPE_ACCOUNT")
	private TypeOf typeOf;

	@Column(name = "PERS_ACCOUNT_PRICE")
	private Float accountPrice;

	@Column(name = "PERS_COMMENTS")
	private String comments;

	@Column(name = "PERS_PRIORITY")
	private TaskPrioritys taskPrioritys;

	@Column(name = "PERS_UPDATE_DATE")
	private Date updateDate;

	@Column(name = "PERS_UPDATE_TIME")
	private Timestamp updateTimestamp;

	@Column(name = "PERS_TARGET_DATE")
	private Date targetDate;

	@Column(name = "PERS_TARGET_TIME")
	private Timestamp targetTime;

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Timestamp getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Timestamp updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public Timestamp getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(Timestamp targetTime) {
		this.targetTime = targetTime;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getPersonName() {
		return personName;
	}

	public void setPersonName(Integer personName) {
		this.personName = personName;
	}

	public Float getPersonCreditAmount() {
		return personCreditAmount;
	}

	public void setPersonCreditAmount(Float personCreditAmount) {
		this.personCreditAmount = personCreditAmount;
	}

	public Float getPersonDreditAmount() {
		return personDreditAmount;
	}

	public void setPersonDreditAmount(Float personDreditAmount) {
		this.personDreditAmount = personDreditAmount;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public String getDebitName() {
		return debitName;
	}

	public void setDebitName(String debitName) {
		this.debitName = debitName;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public TypeOf getTypeOf() {
		return typeOf;
	}

	public void setTypeOf(TypeOf typeOf) {
		this.typeOf = typeOf;
	}

	public Float getAccountPrice() {
		return accountPrice;
	}

	public void setAccountPrice(Float accountPrice) {
		this.accountPrice = accountPrice;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public TaskPrioritys getTaskPrioritys() {
		return taskPrioritys;
	}

	public void setTaskPrioritys(TaskPrioritys taskPrioritys) {
		this.taskPrioritys = taskPrioritys;
	}

}
