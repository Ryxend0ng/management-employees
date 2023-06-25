package com.amis.misa.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.amis.misa.entities.BaseEntity;

@Component
public class BaseSpecification <E extends BaseEntity>{
	public  Specification<E> findByFilter(String filter,List<String> properties ){
		return (root,query,cb)->{
			List<Predicate> predicate= new ArrayList<>();
			for (String prop : properties) {
				predicate.add(cb.or(cb.like(root.get(prop),"%"+filter+"%")));
			}			
			return cb.and(predicate.toArray(new Predicate[] {}));
		};
	}
}
