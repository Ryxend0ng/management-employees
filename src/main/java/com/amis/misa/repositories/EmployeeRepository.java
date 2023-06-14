package com.amis.misa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.amis.misa.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee>{
	public Employee findTop1ByOrderByCreatedDateDesc();
	public Employee findByEmployeeCode(String empCode);
}
