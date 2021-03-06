package com.chansin.web;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.client.RestClientException;



@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ChansinWebClientApplication {

	public static void main(String[] args) throws RestClientException, IOException {
		SpringApplication.run(ChansinWebClientApplication.class, args);
		
		
	}
	
	

}

