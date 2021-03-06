package com.chansin.web.controller;

import java.util.Optional;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chansin.web.dao.EmployeeRepository;
import com.chansin.web.model.Employee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class WebRequestHandler {
	
	@Value("${server.port}")
    private int serverPort;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@RequestMapping(value="/getEmployeeDetails", method=RequestMethod.GET)
	public Iterable<Employee> getEmployeeDetails() {
		
		System.out.println("ChansinWebProject-Instance-port:"+serverPort);
		return employeeRepository.findAll();
		
	}
	
	@RequestMapping(value="/getSingleEmployee/{id}", method=RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getDataFallBack")
	public Employee getSingleEmployee(@PathVariable("id") String id) {
		System.out.println("Inside getSingleEmployee, id= "+id);
		Optional<Employee> emp = employeeRepository.findById(Integer.parseInt(id));
		Employee employee = emp.get();
		return employee;
		
		
	}
	
	
	
	@RequestMapping(value="/addEmployee",method=RequestMethod.POST)
	public String addEmployee(@RequestBody Employee emp) {
		
		Employee employee = new Employee();
		employee.setAge(emp.getAge());
		employee.setName(emp.getName());
		employee.setSex(emp.getSex());
		employee.setDepartment(emp.getDepartment());
		
		employeeRepository.save(employee);
		
		
		return "Saved";
		
	}
	
 public Employee getDataFallBack(String id) {
		
		Employee emp = new Employee();
		emp.setId(Integer.parseInt(id));
		emp.setName("fallback-No employee");
		emp.setAge(0);
		emp.setSex("NA");
		emp.setDepartment("FB Dept.");

		return emp;
		
	}

}
