package com.amis.misa.services;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.amis.misa.dto.EmployeeDto;
import com.amis.misa.entities.Employee;

public interface IEmployeeService extends IBaseService<Employee, Integer,EmployeeDto>{
	public Employee findByEmployeeCode(String empCode);
	public String generateEmployeeCode();
	public byte[]  exportExcelEmployee(ByteArrayOutputStream  stream, List<EmployeeDto> employees);
	public List<String> validateForm(EmployeeDto employeeDto);
	public void setHeaderSheet(Row row,int index,String nameHeader,CellStyle cellStyle,Sheet sheet);
	public <T> void setDataSheet(Row empDataRow,int index,CellStyle cellStyle,T emp);
	public List<Employee> findAll(Specification<Employee> entityByColumn);
}
