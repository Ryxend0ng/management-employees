package com.amis.misa.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amis.misa.dto.DepartmentDto;
import com.amis.misa.entities.Department;
import com.amis.misa.repositories.DepartmentRepository;
import com.amis.misa.services.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService{

	@Autowired
	DepartmentRepository departRepo;
	@Override
	public List<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		
		return departRepo.findAll();
	}

}
