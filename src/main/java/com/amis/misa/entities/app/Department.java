package com.amis.misa.entities.app;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="department")
public class Department extends BaseEntity{
	
	@Column(name = "departmentName", nullable = false)
	private String departmentName;
	
	@Column(name = "departmentCode", nullable = false)
	private String departmentCode;
	
	@JsonIgnore
	@OneToMany(mappedBy = "department",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private List<Employee> emList=new ArrayList<Employee>();
	
	public void addEmployee(Employee employee) {
		emList.add(employee);
		employee.setDepartment(this);
	}
	public void removeEmployee(Employee employee) {
		emList.remove(employee);
		employee.setDepartment(null);
	}
}
