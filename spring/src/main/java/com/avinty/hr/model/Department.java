package com.avinty.hr.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "departments")
@Data
public class Department extends CustomTimeStamps {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 100)
	private String name;

	@NotNull(message = "managerId cannot be null")
	@Column(name = "manager_id")
	private long managerId;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "createdBy")
	private long createdBy;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "updated_by")
	private long updatedBy;

	@ManyToMany(mappedBy = "departments")
	private List<Employee> employees = new ArrayList<>();

	public void addEmployee(Employee emp) {
		employees.add(emp);
	}

	public Department(String name, long createdBy, long updatedBy) {
		this.name = name;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}

}
