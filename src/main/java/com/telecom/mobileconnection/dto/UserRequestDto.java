package com.telecom.mobileconnection.dto;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

public class UserRequestDto {

	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getNewMobileNumber() {
		return newMobileNumber;
	}
	public void setNewMobileNumber(Long newMobileNumber) {
		this.newMobileNumber = newMobileNumber;
	}
	public String getPanCardNo() {
		return panCardNo;
	}
	public void setPanCardNo(String panCardNo) {
		this.panCardNo = panCardNo;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	@Column(unique = true)
	private String emailId;
	private String username;
	private String address;
	private Long newMobileNumber;
	private String panCardNo;
	private Integer planId;
}
