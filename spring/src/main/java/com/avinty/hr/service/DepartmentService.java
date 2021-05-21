package com.avinty.hr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avinty.hr.data.DepartmentRepo;
import com.avinty.hr.model.Department;
import com.avinty.hr.model.DepartmentNotFoundException;

@Service
public class DepartmentService {
	@Autowired
	DepartmentRepo dRepo;

	public List<Department> getAllDepartments() {
		List<Department> dList = new ArrayList<>();
		dRepo.findAll().forEach((dList::add));
		return dList;
	}

	public Department getDepartmentbyId(long id) throws DepartmentNotFoundException {
		return dRepo.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
	}

	public Department saveDepartment(String name, long createdBy, long updatedBy) {
		Department d = new Department(name, createdBy, updatedBy);
		return dRepo.save(d);
	}

	public Department updateDepartment(long id, String name, long createdBy, long updatedBy)
			throws DepartmentNotFoundException {
		Department d = dRepo.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
		d.setName(name);
		d.setCreatedBy(createdBy);
		d.setUpdatedBy(updatedBy);
		return dRepo.save(d);
	}

	public void deleteDepartment(long id) {
		dRepo.deleteById(id);
	}
}
