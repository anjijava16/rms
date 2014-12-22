package com.iwinner.rms.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "AUDIT_INFO")
@Entity
public class Audit implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "iD", unique = true, nullable = false)
	private Integer id;

	@Column(name = "HOSTNAME")
	private String hostname;

	@Column(name = "IpAddress")
	private String ipAddress;

	@Column(name = "ViewDate")
	private Date viewDate;

	@Column(name = "viewDateAndTime")
	private Timestamp viewDateAndTime;

	@Column(name = "Browser")
	private String browser;

	@Column(name = "systemUserName")
	private String systemUser;

	public String getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(String systemUser) {
		this.systemUser = systemUser;
	}

	@Column(name = "UserAgent")
	private String userAgent;

	@Column(name = "VISTORRANID")
	private String vistorranId;

	@Column(name = "OSName")
	private String osName;

	public String getVistorranId() {
		return vistorranId;
	}

	public void setVistorranId(String vistorranId) {
		this.vistorranId = vistorranId;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getViewDate() {
		return viewDate;
	}

	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}

	public Timestamp getViewDateAndTime() {
		return viewDateAndTime;
	}

	public void setViewDateAndTime(Timestamp viewDateAndTime) {
		this.viewDateAndTime = viewDateAndTime;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

}
