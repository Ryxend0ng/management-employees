package com.amis.misa.services;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import com.amis.misa.dto.EmployeeDto;
import com.amis.misa.entities.Employee;

public interface IEmployeeService {
	public Employee findByEmployeeCode(String empCode);
	public List<EmployeeDto> findAllEmployee();
	public Page<Employee> findEmployeebyFilter(Integer pageSize,Integer pageNumber, String employeeFilter);
	public String generateEmployeeCode();
	public boolean saveOrUpdate(EmployeeDto employeeDto);
	public byte[]  exportExcelEmployee(ByteArrayOutputStream  stream, List<EmployeeDto> employees);
	public List<String> validateForm(EmployeeDto employeeDto);
	public int deleteEmployeeById(int employId);
}
