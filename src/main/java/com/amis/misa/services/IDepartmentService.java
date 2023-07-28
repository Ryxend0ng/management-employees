package com.amis.misa.services;

import java.util.List;

import com.amis.misa.dto.DepartmentDto;
import com.amis.misa.entities.app.Department;

public interface IDepartmentService extends IBaseService<Department, Integer,DepartmentDto>{

	List<DepartmentDto> findAll();
	
}
