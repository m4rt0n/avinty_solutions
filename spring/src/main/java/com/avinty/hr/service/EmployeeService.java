package com.avinty.hr.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avinty.hr.data.EmployeeRepo;
import com.avinty.hr.model.Department;
import com.avinty.hr.model.Employee;
import com.avinty.hr.model.EmployeeNotFoundException;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepo eRepo;

	public String hello() {
		return "Hello";

	}

	public void addEntities() {
		Employee e1 = new Employee("e1@mail.com", "e1pw", "e1fname", 1, 1);
		Employee e2 = new Employee("e2@mail.com", "e2pw", "e2fname", 1, 1);
		Employee e3 = new Employee("e3@mail.com", "e3pw", "e3fname", 1, 1);
		Department d1 = new Department("d1name", 1, 1);
		Department d2 = new Department("d2name", 1, 1);
		Department d3 = new Department("d3name", 1, 1);

		e1.setDepartments(Arrays.asList(d1, d2));
		e2.setDepartments(Arrays.asList(d2, d3));
		e3.setDepartments(Arrays.asList(d1, d3));

		eRepo.save(e1);
		eRepo.save(e2);
		eRepo.save(e3);
	}

	public List<Employee> getAllEmployees() {
		List<Employee> eList = new ArrayList<>();
		eRepo.findAll().forEach((eList::add));
		return eList;
	}

	public Employee getEmployeebyId(long id) throws EmployeeNotFoundException {
		return eRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	public Employee saveEmployee(String email, String password, String fullName, long createdBy, long updatedBy) {
		Employee e = new Employee(email, password, fullName, createdBy, updatedBy);
		return eRepo.save(e);
	}

	public Employee updateEmployee(long id, String email, String password, String fullName, long createdBy,
			long updatedBy) throws EmployeeNotFoundException {
		Employee e = eRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		e.setEmail(email);
		e.setPassword(password);
		e.setFullName(fullName);
		e.setCreatedBy(createdBy);
		e.setUpdatedBy(updatedBy);
		return eRepo.save(e);
	}

	public void deleteEmployee(long id) {
		eRepo.deleteById(id);
	}
}
