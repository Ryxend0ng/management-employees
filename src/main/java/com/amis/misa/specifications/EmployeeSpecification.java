package com.amis.misa.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import com.amis.misa.entities.Employee;

@Component
public final class EmployeeSpecification extends BaseSpecification<Employee>{
	
}
