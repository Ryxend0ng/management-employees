package com.amis.misa.specifications;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.amis.misa.dto.SearchCriteriaDto;
import com.amis.misa.entities.app.BaseEntity;
import com.amis.misa.entities.app.Employee;
import com.amis.misa.enums.SearchCriteriaEnum;

@Component
public class BaseSpecification <E extends BaseEntity>{
	
	// lay du lieu loc theo filter
	public  Specification<E> findByFilter(String filter,List<String> properties ){
		return (root,query,cb)->{
			List<Predicate> predicates= properties.stream()
					.map(prop->cb.like(root.get(prop),"%"+filter+"%"))
					.collect(Collectors.toList());
			
//			for (String prop : properties) {
//				predicate.add(cb.like(root.get(prop),"%"+filter+"%"));
//			}			
			return cb.or(predicates.toArray(new Predicate[] {}));
			
		};
	}
	public  Specification<E> findByFilter(Map<String,Optional<String>> map ){
		return (root,query,cb)->{
//			for (Map.Entry<String, String> entry : map.entrySet()) {
//				String prop=entry.getKey();
//				String valueProp=entry.getValue();
//				if(StringUtils.isNotBlank(valueProp)) {
//				if(prop.equals("status")) {
//					predicate.add(cb.equal(root.get(prop),Boolean.valueOf(valueProp)));
//				}else {
//					predicate.add(cb.equal(root.get(prop),valueProp));
//				}
//				}
//				
//			}	
			List<Predicate> predicates  =map.entrySet()
					.stream()
					.filter(entry->StringUtils.isNotBlank(entry.getValue().get()))
					.map(entry -> {
						if(entry.getKey().equals("status")) {
							return (cb.equal(root.get(entry.getKey()),Boolean.valueOf(entry.getValue().get())));
						}
						else {
							return (cb.equal(root.get(entry.getKey()),(entry.getValue().get())));
							}
					})
					.collect(Collectors.toList());
					
			return cb.and(predicates.toArray(new Predicate[] {}));
			
		};
	}
	//lay 1 record theo khoa ngoai
	public  Specification<E> getEnitityByForenkey(String joinTable,SearchCriteriaDto search ){
		return (root,query,cb)->{
			return cb.equal(root.join(joinTable).get(search.getColumn()), search.getValue());
			
			
		};
	}
	// lay 1 record theo column 
	public Specification<E> getEntityByColumn(SearchCriteriaDto search) {
		return (root,query,cb)->{
			return cb.equal(root.get(search.getColumn()), search.getValue());
		};
	}
	// lay  record theo enum operation
		public Specification<E> getEntityByColumn(List<SearchCriteriaDto> searchL,String type) {
			return (root,query,cb)->{
				List<Predicate> preList=new ArrayList<Predicate>();
				for (SearchCriteriaDto search : searchL) {
					switch (search.getOperation()) {
					case EQUAL:
						preList.add(cb.equal(root.get(search.getColumn()), search.getValue()));
						break;
					case LIKE:
						preList.add(cb.like(root.get(search.getColumn()), "%"+search.getValue()+"%"));
						break;
					case IN:
						String[] split=search.getValue().split(",");
						preList.add(root.get(search.getColumn()).in(Arrays.asList(split)));
						break;
					default:
						break;
					}
					preList.add(cb.equal(root.get(search.getColumn()), search.getValue()));
				}
				if(type.equals(SearchCriteriaEnum.AND)) {
					return cb.and(preList.toArray(new Predicate[] {}));
				}else {
					return cb.or(preList.toArray(new Predicate[] {}));
				}
			};
		}
		public Predicate convertToPredicate(Supplier< Specification<E>> func,CriteriaBuilder cb,Root<E> root,CriteriaQuery<?> query) {
			return func.get().toPredicate(root, query, cb);					
		}
}
