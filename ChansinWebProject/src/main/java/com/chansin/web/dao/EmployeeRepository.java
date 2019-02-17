package com.chansin.web.dao;

import org.springframework.stereotype.Repository;

import com.chansin.web.model.Employee;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
