package com.example.reciever;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class TextRestClient {
	  private static final String RESOURCE_PATH = "/rest/employees";
	  private Logger LOG = LoggerFactory.getLogger(TextRestClient.class);
	  private String REQUEST_URI;
	  private RestTemplate restTemplate;
	  
	  public TextRestClient(RestTemplate restTemplate, String host, int port) {
	    this.restTemplate = restTemplate;
	    this.REQUEST_URI = host + ":" + port + RESOURCE_PATH;
	  }
	  
	   public void getForText() {
		   restTemplate.getForEntity("http://localhost:8080/redis/textInfo", String.class);
	  }
}
