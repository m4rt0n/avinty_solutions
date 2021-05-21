package com.avinty.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avinty.hr.model.Employee;
import com.avinty.hr.model.EmployeeNotFoundException;
import com.avinty.hr.service.EmployeeService;

@RestController
@RequestMapping(path = "/API/V1/employees")
public class EmployeeController {
	@Autowired
	EmployeeService eService;

	@GetMapping("/hello")
	public @ResponseBody String hello() {
		return eService.hello();
	}

	@PostMapping("/addstuff")
	public void doStuff() {
		eService.addEntities();
	}

	@GetMapping
	public void getAllEmployees() {
		eService.getAllEmployees();
	}

	@GetMapping("/{id}")
	public void getEmployeeById(@PathVariable("id") long id) throws EmployeeNotFoundException {
		eService.getEmployeebyId(id);
	}

	@PostMapping
	public Employee createEmployee(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, @RequestParam(value = "fullname") String fullName,
			@RequestParam(value = "createdby") long createdBy, @RequestParam(value = "updatedby") long updatedBy) {
		return eService.saveEmployee(email, password, fullName, createdBy, updatedBy);
	}

	@PutMapping("/{id}")
	public Employee updateEmployeeById(@PathVariable("id") long id, @RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, @RequestParam(value = "fullname") String fullName,
			@RequestParam(value = "createdby") long createdBy, @RequestParam(value = "updatedby") long updatedBy)
			throws EmployeeNotFoundException {
		return eService.updateEmployee(id, email, password, fullName, createdBy, updatedBy);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployeeById(@PathVariable("id") long id) {
		eService.deleteEmployee(id);
	}
}
