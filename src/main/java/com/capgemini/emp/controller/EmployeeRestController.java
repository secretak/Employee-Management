package com.capgemini.emp.controller;

import java.util.List;

import com.capgemini.emp.entity.Employee;
import com.capgemini.emp.error.EmployeeNotFoundException;
import com.capgemini.emp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class EmployeeRestController {


	private EmployeeService employeeService;
	// quick and dirty : inject employee dao

	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	@GetMapping("/employees/find5000")
	public List<Employee> findAllBySalray5000() {
		return employeeService.find5000();
	}



	// add mapping for POST /employees - add new employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		employee.setId(0);

		employeeService.save(employee);
		return employee;

	}

	// add mapping for PUT /employees - update existing employee
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {

		employeeService.save(employee);

		return employee;

	}

	// add mapping for DELETE /employees/{employeeId} - delete existing employee
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {

		Employee employee = employeeService.findById(employeeId);
		
		// throw exception if null
		
		if(employee==null) {
			throw new EmployeeNotFoundException("Employee id not found : "+employeeId);
		}
		employeeService.deleteById(employeeId);
		return "Deleted employee id : "+employeeId;
	}

	// add mapping for GET /employees/{employeeId}
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {

		Employee employee = employeeService.findById(employeeId);

		if (employee == null) {
			throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
		}
		return employee;
	}


	@GetMapping("/employee/first-name/{firstName}")
	public Employee searchEmployeeByFirstName(@PathVariable String firstName){
		return employeeService.searchEmployeeByFirstName(firstName);
	}

	@GetMapping("/employee/last-name/{lastName}")
	public Employee searchEmployeeByLastName(@PathVariable String lastName){
		return employeeService.searchEmployeeByLastName(lastName);
	}
}
