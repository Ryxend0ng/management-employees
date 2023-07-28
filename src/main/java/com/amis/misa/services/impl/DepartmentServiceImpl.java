package com.amis.misa.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.amis.misa.dto.DepartmentDto;
import com.amis.misa.entities.app.Department;
import com.amis.misa.repositories.DepartmentRepository;
import com.amis.misa.services.IDepartmentService;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department, Integer, DepartmentRepository,DepartmentDto> implements IDepartmentService{

	
}
