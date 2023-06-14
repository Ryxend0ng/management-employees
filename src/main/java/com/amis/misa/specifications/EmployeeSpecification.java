package com.amis.misa.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import com.amis.misa.entities.Employee;

@Component
public final class EmployeeSpecification {
	public  Specification<Employee> findEmployeesByFilter(String employeesFilter ){
		return (root,query,cb)->{
			List<Predicate> predicate= new ArrayList<>();
			predicate.add(cb.or(cb.like(root.get("employeeCode"),"%"+employeesFilter+"%"),
					cb.like(root.get("employeeName"),"%"+employeesFilter+"%")));
			return cb.and(predicate.toArray(new Predicate[] {}));
		};
	}
}
