package com.chansin.web.fiegn;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="ChansinWebProject")
public interface ChansinWebProxy {
	
	//option-3 code snippet: Commented to check with zuul
	/*
	 *option-3: using feign client
	 * @RequestMapping(method=RequestMethod.GET, value="/chansinwebservices/getEmployeeDetails")
	 *  Object getEmployees();
	 */
	
	
	  @RequestMapping(value="/chansinwebservices/getSingleEmployee/{id}",
	  method=RequestMethod.GET) Object getEmployeeById(@PathVariable("id") String
	  id);
	 
	
	

}
