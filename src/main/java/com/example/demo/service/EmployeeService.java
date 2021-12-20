package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository emrepo;
	
	public List<Employee> listAll()
	{
		return emrepo.findAll();	
	}
	public void save(Employee employee)
	{
		emrepo.save(employee);
	}
	public Employee get(String employeeId)
	{
		return emrepo.findById(employeeId).get();
	}
	public void delete(String employeeId)
	{
		emrepo.deleteById(employeeId);
	}
	public Employee changeUser(Employee employee) {
		return emrepo.saveAndFlush(employee);
	}
}