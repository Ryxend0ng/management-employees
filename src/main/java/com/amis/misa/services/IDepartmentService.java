package com.amis.misa.services;

import java.util.List;

import com.amis.misa.dto.DepartmentDto;
import com.amis.misa.entities.Department;

public interface IDepartmentService {
	public List<Department> getAllDepartments();
}
