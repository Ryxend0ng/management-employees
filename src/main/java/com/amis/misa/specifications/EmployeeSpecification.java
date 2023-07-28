package com.amis.misa.specifications;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.amis.misa.dto.SearchAndSortEmployeeDto;
import com.amis.misa.dto.SearchCriteriaDto;
import com.amis.misa.entities.app.Employee;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Component
public final class EmployeeSpecification extends BaseSpecification<Employee>{
	// lay du lieu loc theo filter
		public  Specification<Employee> sortWithMultipeConditional(SearchAndSortEmployeeDto searchSort ){
			return (root,query,cb)->{
//				
//				if(StringUtils.isNotBlank(searchSort.getDepartmentName())) {
//					predicate.add(convertToPredicate(()->getEnitityByForenkey("department", new SearchCriteriaDto("departmentName", searchSort.getDepartmentName())), cb, root, query));					
//				}
//				if(StringUtils.isNotBlank(searchSort.getDepartmentName())) {		
//					predicate.add(convertToPredicate(()->getEnitityByForenkey("position", new SearchCriteriaDto("positionName", searchSort.getPositionName())), cb, root, query));									
//				}
				
				List<Predicate> predicates= Stream.of(
					Optional.ofNullable(searchSort.getDepartmentName())
							.filter(depart->!depart.isBlank())
							.map(department->convertToPredicate(()->getEnitityByForenkey("department",
							new SearchCriteriaDto("departmentName", searchSort.getDepartmentName().trim())),
							cb, root, query)),
					Optional.ofNullable(searchSort.getPositionName())
							.filter(depart->!depart.isBlank())
							.map(positionName->convertToPredicate(()->getEnitityByForenkey("position", new SearchCriteriaDto("positionName", searchSort.getPositionName().trim())), cb, root, query))				
				)
						.filter(Optional::isPresent)
						.map(Optional::get)
						.collect(Collectors.toList());
				utilsSearch(cb, root,query, predicates, searchSort);
				return cb.and(predicates.toArray(new Predicate[] {}));
				
			};
		}

		public void utilsSearch(CriteriaBuilder cb,Root<Employee> root,CriteriaQuery<?> query,List<Predicate> predicate,SearchAndSortEmployeeDto searchSort) {
			Map<String,Optional<String>> map=new HashMap<>();
			map.put("employeeCode",Optional.ofNullable(searchSort.getEmployeeCode()));
			map.put("status",Optional.ofNullable(String.valueOf(searchSort.getStatus())));
			predicate.add(convertToPredicate(()-> findByFilter(map), cb, root, query));
			if(searchSort.getBeginDate()!=null && searchSort.getEndDate()!=null) {
				predicate.add(cb.between(root.get("createdDate"), searchSort.getBeginDate(), searchSort.getEndDate()));
			}
		}
		
}
