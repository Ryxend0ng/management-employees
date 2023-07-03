package com.amis.misa.specifications;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.amis.misa.dto.SearchCriteriaDto;
import com.amis.misa.entities.BaseEntity;
import com.amis.misa.enums.SearchCriteriaEnum;

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
		
}
