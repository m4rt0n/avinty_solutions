package com.avinty.hr.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee extends CustomTimeStamps {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 255)
	private String email;

	@Column(length = 66)
	private String password;

	@Column(name = "full_name", length = 200)
	private String fullName;

	@NotNull(message = "depId cannot be null")
	@Column(name = "dep_id")
	private long depId;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "createdBy")
	private long createdBy;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "updated_by")
	private long updatedBy;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "employee_department", joinColumns = @JoinColumn(name = "emp_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "dep_id", referencedColumnName = "id"))
	private List<Department> departments = new ArrayList<>();

	public void addDepartment(Department dept) {
		departments.add(dept);
	}

	public Employee(String email, String password, String fullName, long createdBy, long updatedBy) {
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}

}
