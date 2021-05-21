package com.avinty.hr.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.avinty.hr.model.Employee;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long> {

}
