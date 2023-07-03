package com.amis.misa.controller.versions.v1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amis.misa.dto.SearchCriteriaDto;
import com.amis.misa.entities.Employee;
import com.amis.misa.services.IEmployeeService;
import com.amis.misa.services.impl.EmployeeServiceImpl;
import com.amis.misa.specifications.BaseSpecification;


@RestController
public class TestController {
	@Autowired
	IEmployeeService emp;
	@GetMapping("test")
	public ResponseEntity<?> get(@RequestBody SearchCriteriaDto search){
		BaseSpecification<Employee> base=new BaseSpecification<Employee>();
		return ResponseEntity.ok(emp.findAll(base.getEnitityByForenkey("department",search)));
	}
	@GetMapping("testemp/{emp1}")
	public Employee get(@PathVariable String emp1){
		return emp.findByEmployeeCode(emp1);
	}
	public static void main(String[] args) {
	    // assume SLF4J is bound to logback in the current environment
	  
	}
}
