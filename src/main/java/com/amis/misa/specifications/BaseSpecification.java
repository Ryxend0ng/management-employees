package com.amis.misa.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.amis.misa.entities.BaseEntity;

@Component
public class BaseSpecification <E extends BaseEntity>{
	
	// lay du lieu loc theo filter
	public  Specification<E> findByFilter(String filter,List<String> properties ){
		return (root,query,cb)->{
			List<Predicate> predicate= new ArrayList<>();
			
			for (String prop : properties) {
				predicate.add(cb.like(root.get(prop),"%"+filter+"%"));
			}			
			return cb.or(predicate.toArray(new Predicate[] {}));
			
		};
	}
	//lay 1 record theo khoa ngoai
	public  Specification<E> getEnitityByForenkey(String forenKey ){
		return (root,query,cb)->{
			List<Predicate> predicate= new ArrayList<>();
			//root.join(null)
			return cb.or(predicate.toArray(new Predicate[] {}));
			
		};
	}
}
