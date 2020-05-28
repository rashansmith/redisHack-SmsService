package com.example.repository;

import java.util.List;
import java.util.Map;

import com.example.models.Customer;

public interface CustomerRepository {
	Map<Long, Customer> findAllCustomers();

	void add(Customer customer);

	void delete(String id);

	Customer findCustomer(Long id);
	
	void unsubCustomer(String phoneNum);
	
	List<String> findSubscribedCustomers();


}
