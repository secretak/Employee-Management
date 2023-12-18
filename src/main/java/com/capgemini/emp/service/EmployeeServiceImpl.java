package com.capgemini.emp.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.emp.dao.EmployeeDAO;
import com.capgemini.emp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO;
	@Autowired
	public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}

	@Override
	@Transactional
	public List<Employee> find5000() {
		return employeeDAO.findAll().stream().filter(s->s.getSalary()>5000).sorted(Comparator.comparing(Employee::getFirstName)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		return employeeDAO.findById(theId);
	}

	@Override
	@Transactional
	public void save(Employee employee) {
		employeeDAO.save(employee);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		employeeDAO.deleteById(theId);
	}

	@Override
	@Transactional
	public Employee searchEmployeeByFirstName(String firstName) {
		return employeeDAO.findByFirstName(firstName);
	}

	@Override
	@Transactional
	public Employee searchEmployeeByLastName(String lastName) {
		return employeeDAO
				.findByLastName(lastName);
	}
}
