package com.example.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.models.Customer;

@Service
public class CustomerService implements CustomerRepository {

	private static final String TABLE_NAME = "Customer";

	private RedisTemplate<String, Object> redisTemplate;

	private HashOperations<String, Long, Customer> hashOperations;

     
    @Autowired
    public CustomerService(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }
 
    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }
    
	@Override
	public Map<Long, Customer> findAllCustomers() {
		  return hashOperations.entries(TABLE_NAME);
	}

	@Override
	public void add(Customer customer) {
		 hashOperations.put(TABLE_NAME, customer.getId(), customer);
	}

	@Override
	public Customer findCustomer(Long id) {
		 return (Customer) hashOperations.get(TABLE_NAME, id);
	}

	@Override
	public void delete(String id) {
	        hashOperations.delete(TABLE_NAME, id);
	}

	@Override
	public void unsubCustomer(String phoneNum) {
		Map<Long, Customer> v = hashOperations.entries(TABLE_NAME);
		 System.out.println("Subs: " + v.toString());
		/* (Entry<Long, Customer> entry : v.entrySet()) {
			Customer c = (Customer) entry.getValue();
			 if(c.getPhoneNumber() == phoneNum) {
				 System.out.println("Subs: " + entry.getValue().getPhoneNumber().toString());
				 hashOperations.delete(TABLE_NAME, c.getId());
				 //entry.getValue().setPhoneNumber(phoneNum);
			 }
		}*/
	
			for (Entry<Long, Customer> entry : v.entrySet()) {
				Customer c = entry.getValue();
				 if(c.getPhoneNumber().equalsIgnoreCase(phoneNum)) {
					// System.out.println(entry.getKey() + " : " + entry.getValue().getPhoneNumber());
					 hashOperations.delete(TABLE_NAME, c.getId()); 
					// c.setSubscription(false);
					// System.out.println(entry.getKey() + " : " + entry.getValue().getPhoneNumber());
					// entry.getValue().setSubscription(false);
					// hashOperations.put(TABLE_NAME,  entry.getValue().getId(), c);
				 System.out.println(entry.getKey() + " : " + entry.getValue().getPhoneNumber());
				// list.add(entry.getValue().getPhoneNumber());
				}
			}
	}
	
	public List<String> findSubscribedCustomers() {
		List<String> list = new ArrayList<String>();
		Map<Long, Customer> v = hashOperations.entries(TABLE_NAME);
		for (Entry<Long, Customer> entry : v.entrySet()) {
			Customer c = entry.getValue();
			 if(c.getSubscription() == true) {
			 System.out.println(entry.getKey() + " : " + entry.getValue().getPhoneNumber());
			 list.add(entry.getValue().getPhoneNumber());
			}
		}
		return list;
	}

}
