package com.chansin.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
public class ChansinWebProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChansinWebProjectApplication.class, args);
		
		
		
	}

}

