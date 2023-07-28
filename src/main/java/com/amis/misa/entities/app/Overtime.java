package com.amis.misa.entities.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name ="overtime")
public class Overtime extends BaseEntity{
	
	@Column(name = "hours", nullable = true)
	Integer hours;
	
	@Column(name = "curenDatetOt", nullable = true)
	LocalDate curenDatetOt;
	
	@OneToMany(mappedBy = "department",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Employee> emList=new ArrayList<Employee>();
	
	public void addEmployee(Employee employee) {
		emList.add(employee);
		employee.setOverTime(this);
	}
	public void removeEmployee(Employee employee) {
		emList.remove(employee);
		employee.setOverTime(null);
	}
}
