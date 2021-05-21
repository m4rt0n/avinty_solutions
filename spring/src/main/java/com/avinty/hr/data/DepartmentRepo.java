package com.avinty.hr.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.avinty.hr.model.Department;

@Repository
public interface DepartmentRepo extends CrudRepository<Department, Long> {

}
