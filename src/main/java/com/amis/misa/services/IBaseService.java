package com.amis.misa.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import com.amis.misa.dto.BaseDto;
import com.amis.misa.entities.BaseEntity;

public interface IBaseService <E extends BaseEntity, ID extends Serializable,K extends BaseDto>{
	public Optional<E> findById(ID id);
	public List<K> findAll();
	public int deleteById(ID id);
	public boolean save(K dto);
	public boolean update(K dto);
	public Optional<Page<E>> findEntitiesByFilter(Integer pageSize,Integer pageNumber,@Nullable String filter,@Nullable List<String> prop);
}
