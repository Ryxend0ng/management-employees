package com.amis.misa.services.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

import com.amis.misa.converter.ObjectConvert;
import com.amis.misa.dto.BaseDto;
import com.amis.misa.dto.EmployeeDto;
import com.amis.misa.entities.BaseEntity;

import com.amis.misa.repositories.BaseRepository;
import com.amis.misa.services.IBaseService;
import com.amis.misa.specifications.BaseSpecification;

public class BaseServiceImpl<E extends BaseEntity, ID extends Serializable, R extends BaseRepository<E, ID>,K extends BaseDto> implements IBaseService<E, ID,K>{

	@Autowired
	protected R repository;
	@Autowired
	BaseSpecification<E> baseSpec;
	
	private ObjectConvert< E, K> convert;
	@Autowired
	public void setConvert(ObjectConvert<E, K> convert) {
		this.convert=convert;
	}
	
	@Override
	public List<K> findAll() {
		// TODO Auto-generated method stub
		List<K> list=new ArrayList<>();
		repository.findAll().forEach(e->list.add(convert.convertToDto(e)));
		return list;
	}

	@Override
	public int deleteById(ID id) {
		// TODO Auto-generated method stub
	    if(repository.findById(id).isPresent()) { 
	    	repository.deleteById(id);
	    	return 1;
	    }else {
	    	return 0;
	    }
	}

	@Override
	public boolean saveOrUpdate(K dto) {
		// TODO Auto-generated method stub
		return repository.save(convert.convertToEntity(dto)) != null;
	}

	@Override
	public Optional<Page<E>> findEntitiesByFilter(Integer pageSize, Integer pageNumber,@Nullable String filter,@Nullable List<String> prop) {
		// TODO Auto-generated method stub
		Pageable page=PageRequest.of(pageNumber-1, pageSize);
		return Optional.ofNullable(repository.findAll(baseSpec.findByFilter(filter,prop),page));
		
	}

	@Override
	public Optional<E> findById(ID id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	
}
