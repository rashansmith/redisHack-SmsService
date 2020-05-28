package com.example.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;

import com.example.models.*;
import com.example.repository.BusinessService;
import com.example.repository.CustomerService;
import com.example.sender.RedisSender;
import com.example.twilio.SmsSender;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

@RestController
@ApplicationScope
@Component
@RequestMapping("/redis")
public class RedisController {

	@Autowired
	BusinessService businessRepo;

	@Autowired
	CustomerService customerRepo;
	
	@Autowired
	RedisSender sender;
	
	@Autowired
	SmsSender smsSender;
	
	
	/************************************Topic Queue *************************************/
	
	@GetMapping("/add")
	public String sendDataToRedisQueue(@RequestParam("redis") String input) {
		sender.sendDataToRedisQueue(input);
		return "successfully sent";
	}
	
	
	@GetMapping("/createDeal")
	public String createDeal(@RequestParam("businessId") Long businessId,
			@RequestParam("title") String title,
			@RequestParam("details") String details,
			@RequestParam("couponCode") String couponCode,
			@RequestParam("expirationDate") String expirationDate) {
		
		Business business = fetchBusiness(businessId);
		DealMessage deal = new DealMessage(title, business.getName(), business.getCity(),
				business.getAddress(), details, couponCode, expirationDate);
		String dealM = business.createDeal(deal);
		
		//sender.sendDataToRedisQueue(dealM);
		smsSender.sendMessage(dealM);
		return "Your deal has been added";
	}
	
	@GetMapping("/form")
	public String getForm() {
		return "index";
	}
	
	@RequestMapping(value = "/customerS", method = RequestMethod.GET)
	public String sendForm() {
		return "customer";
	}
	
	@RequestMapping(value = "/businessS", method = RequestMethod.GET)
	public String sendForm2() {
		return "business";
	}

	
	/************************************Text Messaging *********************************/
	@RequestMapping(value = "/textInfo", produces = "application/xml", method = RequestMethod.GET)
	public void respondToText() {

		try {
			smsSender.sendMessage("Your text was successful!!");
		} catch (Exception ex) {
		}
	}
	
	@RequestMapping(value = "/textDeals", method = RequestMethod.GET)
	public String textDeals(@RequestParam("deal") String deal) {

		try {
			smsSender.sendMessage(deal);
			
		} catch (Exception ex) {
		}
		return "done!";
	}
	
	@RequestMapping(value = "/text", produces = "application/xml", method = RequestMethod.POST)
	public void textDealsToCustomers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String body = request.getParameter("Body");
		String message = smsSender.processRequest(body);

		Body messageBody = new Body.Builder(message).build();
		Message sms = new Message.Builder().body(messageBody).build();
		MessagingResponse twiml = new MessagingResponse.Builder().message(sms).build();

		response.setContentType("application/xml");

		try {
			response.getWriter().print(twiml.toXml());
		} catch (TwiMLException e) {
			e.printStackTrace();
		}
	}

	
	/*************************Business Interactions ******************************/
	
	@RequestMapping(value = "/addBusiness", method = RequestMethod.POST)
	public String addBusiness(@RequestParam long id, @RequestParam String name,
			@RequestParam String address, @RequestParam String city, @RequestParam String category,
			@RequestParam String phoneNum, @RequestParam String website) {

		Business business = new Business(id, name, address, city, category, phoneNum, website);

		businessRepo.add(business);
		return "Your business has been added";
	}

	@RequestMapping("/businesses")
	public @ResponseBody Map<Long, Business> findAll() {
		Map<Long, Business> aa = businessRepo.findAllBusinesses();
		Map<Long, Business> map = new HashMap<Long, Business>();
		for (Entry<Long, Business> entry : aa.entrySet()) {
			Long key = entry.getKey();
			map.put(key, aa.get(key));
		}
		return map;
	}

	@Cacheable(key = "#id", value = "businesses", unless = "#result.id < 1200")
	@GetMapping(path = "/business/{id}")
	public Business fetchBusiness(@PathVariable("id") long id) {
		return businessRepo.findBusiness(id);
	}


	/***********************Customer Interactions********************/
	
	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public String addCustomer( @RequestParam String name,
			@RequestParam String email, @RequestParam String phoneNumber) {

		Customer customer = new Customer(name, email, phoneNumber);

		customerRepo.add(customer);
		//return new ResponseEntity<>(HttpStatus.OK);
		return "You have been added";
	}
	
	@RequestMapping(value = "/removeCustomer", method = RequestMethod.GET)
	public String removeCustomer(@RequestParam String phoneNumber) {
		customerRepo.unsubCustomer(phoneNumber);
		//return new ResponseEntity<>(HttpStatus.OK);
		return "You have been removed";
	}

	@RequestMapping("/customers")
	public @ResponseBody Map<Long, Customer> findAllCustomers() {
		Map<Long, Customer> aa = customerRepo.findAllCustomers();
		Map<Long, Customer> map = new HashMap<Long, Customer>();
		for (Entry<Long, Customer> entry : aa.entrySet()) {
			Long key = entry.getKey();
			map.put(key, aa.get(key));
		}
		return map;
	}

	@Cacheable(key = "#id", value = "customers", unless = "#result.id < 1200")
	@GetMapping(path = "/customer/{id}")
	public Customer fetchCustomers(@PathVariable("id") long id) {
		return customerRepo.findCustomer(id);
	}
}