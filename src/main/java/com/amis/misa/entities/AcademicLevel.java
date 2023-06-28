package com.amis.misa.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name ="academic_level")
public class AcademicLevel extends BaseEntity{
	
	@Column(name = "level", nullable = false)
	String level;
	
	@OneToMany(mappedBy = "position",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Employee> emList=new ArrayList<Employee>();
	
	public void addEmployee(Employee employee) {
		emList.add(employee);
		employee.setAcademicLevel(this);
	}
	public void removeEmployee(Employee employee) {
		emList.remove(employee);
		employee.setAcademicLevel(null);
	}
}
