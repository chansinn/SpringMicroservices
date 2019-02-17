package com.chansin.web.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.chansin.web.zuul.filters.ErrorFilter;
import com.chansin.web.zuul.filters.PostFilter;
import com.chansin.web.zuul.filters.PreFilter;
import com.chansin.web.zuul.filters.RouteFilter;


@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ChansinZuulServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChansinZuulServiceApplication.class, args);
	}
	
	@Bean
	public PreFilter prefilter() {
		return new PreFilter();
	}
	
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}

}

