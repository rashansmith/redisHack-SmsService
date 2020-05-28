package com.example.reciever;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.controller.RedisController;
import com.example.twilio.SmsSender;

@Service
public class RedisReciever implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisReciever.class);
	
	@Autowired
	RedisController controller;
	  
	@Autowired
	SmsSender smsSender;
	
	TextRestClient client;
	
	RestTemplate restTemplate = new RestTemplate();
	
	String result1;
	@Override
	public void onMessage(Message message, byte[] pattern) {

		result1 = message.toString();
		LOGGER.info("Received data - " + message.toString() + " from Topic - " + new String(pattern));
		        
		 final String uri2 = "http://localhost:8080/redis/textDeals";
		 UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri2)
			        .queryParam("deal", result1);
		
		 String result = restTemplate.getForObject(builder.toUriString(), String.class);

		 System.out.println(result);
	}
	
	public void handleMessage(String text) {

	}
}
