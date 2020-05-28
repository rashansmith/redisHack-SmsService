package com.example.repository;

import java.util.Map;

import com.example.models.Business;

public interface BusinessRepository {
	 
    Map<Long, Business> findAllBusinesses();
 
    void add(Business business);
 
    Business findBusiness(Long id);
     
}
