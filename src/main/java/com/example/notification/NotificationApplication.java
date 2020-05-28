package com.example.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = { "com.example.sender", "com.example.reciever", "com.example.configuration", 
		"com.example.controller", "com.example.service", "com.example.twilio", 
		"com.example.models","com.example.repository" })
@EntityScan(basePackages = "com.example.models")
public class NotificationApplication {

		public static void main(String[] args) {
			SpringApplication.run(NotificationApplication.class, args);
		}
}
