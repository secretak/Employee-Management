package com.capgemini.emp.dao;

import java.util.List;

import com.capgemini.emp.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDAO extends JpaRepository<Employee,Integer> {
	
	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public Employee save(Employee employee);
	
	public void deleteById(int theId);

	public Employee findByFirstName(String firstName);
	public Employee findByLastName(String lastName);



}
