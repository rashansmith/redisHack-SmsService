package com.example.repository;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.models.Business;

@Service
public class BusinessService implements BusinessRepository{

	private static final String TABLE_NAME = "Business";

	private RedisTemplate<String, Object> redisTemplate;

	private HashOperations<String, Long, Business> hashOperations;

     
    @Autowired
    public BusinessService(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }
 
    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }
     
    @Override
	public void add(Business business) {
        hashOperations.put(TABLE_NAME, business.getId(), business);
    }
 
     
	public Business findBusiness(Long id){
        return (Business) hashOperations.get(TABLE_NAME, id);
    }
	
    public Map<Long, Business> findAllBusinesses(){
        return hashOperations.entries(TABLE_NAME);

    }

	
  
}
