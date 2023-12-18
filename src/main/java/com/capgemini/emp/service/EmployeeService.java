package com.capgemini.emp.service;

import com.capgemini.emp.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;



public interface EmployeeService {
	
	public List<Employee> findAll();

	public List<Employee> find5000();
	
	public Employee findById(int theId);
	
	public void save(Employee employee);
	
	public void deleteById(int theId);

	public Employee searchEmployeeByFirstName(String firstName);
	public Employee searchEmployeeByLastName(String lastName);
}
