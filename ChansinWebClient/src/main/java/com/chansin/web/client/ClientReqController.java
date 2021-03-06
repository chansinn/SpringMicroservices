package com.chansin.web.client;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.chansin.web.fiegn.ChansinWebProxy;

@RestController
public class ClientReqController {
	
	
	 // Option-1: Eureka Implementation( Also goes with Zuul implementation)
	  @Autowired private DiscoveryClient discoveryClient;
	 
	
	
	 // Option-2: Ribbon implementation
	  @Autowired private LoadBalancerClient loadBalancer;
	 
	//Option-3: Feign Client Implementation
	@Autowired
	private ChansinWebProxy loadBalancer1;
	

	@RequestMapping(value="/employees", method=RequestMethod.GET)
	public Object getEmployee() throws RestClientException, IOException {
		System.out.println("Inside getEmployee=========");
		
		//String baseUrl = "http://localhost:8080/getEmployeeDetails";
		
		
		
		  //Option-1: code with zuul
		//Can't use feign client with zuul as both zuul and feign internally use Eureka service registry and ribbon.
		
		  
		  List<ServiceInstance> instances =
		  discoveryClient.getInstances("ChansinZuul-Service");
		  ServiceInstance instance =
		  instances.get(0); String baseUrl = instance.getUri().toString();
		  
		  baseUrl = baseUrl+"/chansinwebservices/getEmployeeDetails";
		  System.out.println("BaseUrl: "+baseUrl);
		  RestTemplate restTemplate = new RestTemplate();
		  ResponseEntity<String> response=null; 
		  try{ 
			  response=restTemplate.exchange(baseUrl, HttpMethod.GET,getHeaders(),String.class);
			 }catch (Exception ex) {
				  System.out.println(ex);
			 }
		  return response;
		 
		
		//Option-2 code: Eureka+Ribbon implementation
		/*
		 * 
		 * ServiceInstance serviceInstance = loadBalancer.choose("ChansinWebProject");
		 * 
		 * System.out.println("Logging serverInstance from LB: >>>====== "
		 * +serviceInstance.getUri());
		 * 
		 * String baseUrl = serviceInstance.getUri().toString();
		 * 
		 * baseUrl = baseUrl+"/getEmployeeDetails";
		 * 
		 * RestTemplate restTemplate = new RestTemplate(); ResponseEntity<String>
		 * response=null; try{ response=restTemplate.exchange(baseUrl, HttpMethod.GET,
		 * getHeaders(),String.class); }catch (Exception ex) { System.out.println(ex); }
		 */
		
		//option-3: through feign client
		//return loadBalancer1.getEmployees();
	}
	
	@RequestMapping(value="/getResourceById/{id}", method=RequestMethod.GET)
	public Object getEmployeeById(@PathVariable("id") String id) {
		//option-2 code Eureka with Ribbon Implementation
		/*
		 * ServiceInstance serviceInstance = loadBalancer.choose("ChansinWebProject");
		 * String baseUrl = serviceInstance.getUri().toString();
		 * 
		 * baseUrl = baseUrl+"/getSingleEmployee/" + id;
		 * System.out.println("Web-Client baseUrl"+baseUrl);
		 * 
		 * RestTemplate restTemplate = new RestTemplate(); ResponseEntity<String>
		 * response=null; try{ response=restTemplate.exchange(baseUrl, HttpMethod.GET,
		 * getHeaders(),String.class); }catch (Exception ex) { System.out.println(ex); }
		 * return response;
		 */
		
		//option-3 Feign Client(Eureka+Ribbon+Feign) Implementation
		
		return loadBalancer1.getEmployeeById(id);
		
		
		
	}

	private HttpEntity<?> getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}
