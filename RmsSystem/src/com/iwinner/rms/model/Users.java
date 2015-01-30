package com.iwinner.rms.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class Users {

	@Id
	@Column(name = "NAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "LAST_MODIFIED_TIME")
	private Timestamp lastModifiedTime;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name = "ACCOUNTSTATUS")
	private String accountStatus;
	@Column(name = "USER_COMMENT")
	private String userComments;
	@Column(name = "CONSECUTIVELOGINFAILURES")
	private Integer consecutiveLoginFailures;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "EXPIRATIONDATE")
	private Date expirationDate;
	@Column(name = "EXPIREPASSWORD")
	private Integer expirePassword;
	@Column(name = "FULLNAME")
	private String fullName;
	@Column(name = "LASTLOGIN")
	private Timestamp lastLogin;
	@Column(name = "LASTPASSWORDCHANGEDATE")
	private Date lastPasswordChangedDate;
	@Column(name = "PHONE")
	private String phone;

	@Column(name = "ROLE")
	private String role;

	@Column(name = "PAST_PASSWORDS")
	private String pastPasswords;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Timestamp lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getUserComments() {
		return userComments;
	}

	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}

	public Integer getConsecutiveLoginFailures() {
		return consecutiveLoginFailures;
	}

	public void setConsecutiveLoginFailures(Integer consecutiveLoginFailures) {
		this.consecutiveLoginFailures = consecutiveLoginFailures;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Integer getExpirePassword() {
		return expirePassword;
	}

	public void setExpirePassword(Integer expirePassword) {
		this.expirePassword = expirePassword;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getLastPasswordChangedDate() {
		return lastPasswordChangedDate;
	}

	public void setLastPasswordChangedDate(Date lastPasswordChangedDate) {
		this.lastPasswordChangedDate = lastPasswordChangedDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	

	public String getPastPasswords() {
		return pastPasswords;
	}

	public void setPastPasswords(String pastPasswords) {
		this.pastPasswords = pastPasswords;
	}

	@Override
	public String toString() {
		return "Users [username=" + username + ", password=" + password
				+ ", userId=" + userId + ", lastModifiedTime="
				+ lastModifiedTime + ", lastUpdatedBy=" + lastUpdatedBy
				+ ", accountStatus=" + accountStatus + ", userComments="
				+ userComments + ", consecutiveLoginFailures="
				+ consecutiveLoginFailures + ", email=" + email
				+ ", expirationDate=" + expirationDate + ", expirePassword="
				+ expirePassword + ", fullName=" + fullName + ", lastLogin="
				+ lastLogin + ", lastPasswordChangedDate="
				+ lastPasswordChangedDate + ", phone=" + phone + ", role="
				+ role + ", pastPasswords=" + pastPasswords + "]";
	}

	
	
}
