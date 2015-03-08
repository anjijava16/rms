package com.iwinner.rms.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITEM_INFO")
public class ItemInfo implements java.io.Serializable {
	@Id
	@Column(name = "ITEM_ID")
	private Integer itemId;

	@Column(name = "ITEAMNAME")
	private String itemName;
	@Column(name = "ITEM_TAKEN_PERSON_NAME")
	private String itemTakenPerson;

	@Column(name = "PRICE")
	private Float price;
	@Column(name = "TAKING_PALCE")
	private String takingPlace;
	@Column(name = "PERSONS_WITH")
	private String personsWith;
	@Column(name = "TAKEN_DATE")
	private Date date;
	@Column(name = "TAKEN_TIME")
	private Timestamp takenTime;
	@Column(name = "WHERE_PURCHASE")
	private String purchasePlace;
	@Column(name = "UPDATE_BY")
	private String updatedBy;
	@Column(name = "USERNAME")
	private String username;
	@Column(name = "UPDATED_TIME")
	private Timestamp updatedTime;
	@Column(name = "COMMENTS")
	private String comments;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemTakenPerson() {
		return itemTakenPerson;
	}

	public void setItemTakenPerson(String itemTakenPerson) {
		this.itemTakenPerson = itemTakenPerson;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getTakingPlace() {
		return takingPlace;
	}

	public void setTakingPlace(String takingPlace) {
		this.takingPlace = takingPlace;
	}

	public String getPersonsWith() {
		return personsWith;
	}

	public void setPersonsWith(String personsWith) {
		this.personsWith = personsWith;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Timestamp getTakenTime() {
		return takenTime;
	}

	public void setTakenTime(Timestamp takenTime) {
		this.takenTime = takenTime;
	}

	public String getPurchasePlace() {
		return purchasePlace;
	}

	public void setPurchasePlace(String purchasePlace) {
		this.purchasePlace = purchasePlace;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "ItemInfo [itemId=" + itemId + ", itemName=" + itemName
				+ ", itemTakenPerson=" + itemTakenPerson + ", price=" + price
				+ ", takingPlace=" + takingPlace + ", personsWith="
				+ personsWith + ", date=" + date + ", takenTime=" + takenTime
				+ ", purchasePlace=" + purchasePlace + ", updatedBy="
				+ updatedBy + ", username=" + username + ", updatedTime="
				+ updatedTime + "]";
	}

}
