package com.amis.misa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.amis.misa.entities.app.Employee;

@Repository(value = "empRepo")
public interface EmployeeRepository extends BaseRepository<Employee, Integer>{
	public Employee findTop1ByOrderByCreatedDateDesc();
	public Employee findByEmployeeCode(String empCode);
	public Employee findByEmployeeName(String name);// select employee_name from  employee where employee_name=name;
}
