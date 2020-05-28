package com.example.models;

public class DealMessage {

	public String title; 
	public String businessName;
	public String businessCity;
	public String businessAddress;
	public String details;
	public String couponCode;
	public String expirationDate;
	
	
	public DealMessage(String title, String businessName, String businessCity, String businessAddress, String details,
			String couponCode, String expirationDate) {
		super();
		this.title = title;
		this.businessName = businessName;
		this.businessCity = businessCity;
		this.businessAddress = businessAddress;
		this.details = details;
		this.couponCode = couponCode;
		this.expirationDate = expirationDate;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getBusinessCity() {
		return businessCity;
	}
	public void setBusinessCity(String businessCity) {
		this.businessCity = businessCity;
	}
	public String getBusinessAddress() {
		return businessAddress;
	}
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
}
