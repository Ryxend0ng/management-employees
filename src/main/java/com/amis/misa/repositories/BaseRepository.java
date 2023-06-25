package com.amis.misa.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import com.amis.misa.entities.BaseEntity;

public interface BaseRepository <E extends BaseEntity, ID extends Serializable> extends JpaRepository<E, ID>,JpaSpecificationExecutor<E>{
	
}
