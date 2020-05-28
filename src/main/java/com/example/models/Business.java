package com.example.models;


import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Deals")
public class Business implements Serializable  {
	
	@Autowired
	DealMessage deal;

	private static final long serialVersionUID = 1L;
	
	public Long id;
	public String name;
	public String address;
	public String city;
	public String category;
	public String phoneNumber;
	public String website;
	
	public Business(Long id, String name, String address, String city, String category, String phoneNumber, String website) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.category = category;
		this.phoneNumber = phoneNumber;
		this.website = website;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}

	public String getWebsite() {
		return this.website;
	}
	public String createDeal(DealMessage deal) {
		return "NEW DEAL ALERT " +   "\n" + 
				"Business Name: " + this.getName() + "\n" + 
				"Business City: " + this.getCity() + "\n" +
				"Business Category: " + this.getCategory() + "\n" +
				"Business Address: " + this.getAddress() + "\n" +
				"Business website:" + this.getWebsite() + "\n" +
				"Deal Info: " + deal.getTitle() + "\n" +
				"Coupon Code: " + deal.getCouponCode() + "\n" +
				"Expiration Date: " + deal.getExpirationDate();
	}

	@Override
	public String toString() {
		return "Business [name=" + name + ", address=" + address + ", city=" + city + ", category=" + category
				+ ", phoneNumber=" + phoneNumber + "]";
	}

}
