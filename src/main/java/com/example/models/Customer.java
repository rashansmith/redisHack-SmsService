package com.example.models;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Customer")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	public Long id;
	public String name;
	public String email;
	public String phoneNumber;
	public boolean subscription;
	
	public Customer(String name, String email, String phoneNumber ) {
		Random ran = new Random();
		
		this.id = ran.nextLong();
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.subscription = true;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean getSubscription() {
		return true;
	}

	public void setSubscription(boolean subscription) {
		this.subscription = subscription;
	}
	
}
