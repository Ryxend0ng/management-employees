package com.amis.misa.services.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.amis.misa.converter.ObjectConvert;
import com.amis.misa.dto.EmployeeDto;
import com.amis.misa.entities.Employee;
import com.amis.misa.repositories.EmployeeRepository;
import com.amis.misa.services.IEmployeeService;
import com.amis.misa.specifications.EmployeeSpecification;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

	@Autowired
	EmployeeRepository employeeRepo;

	@Autowired
	EmployeeSpecification employeeSpecification;
	 
	private ObjectConvert< Employee, EmployeeDto> employeeConvert=new ObjectConvert<Employee, EmployeeDto>(Employee.class, EmployeeDto.class);
	
	@Override
	public List<EmployeeDto> findAllEmployee() {
		// TODO Auto-generated method stub
		List<EmployeeDto> list=new ArrayList<EmployeeDto>();
		employeeRepo.findAll().forEach(e->list.add(employeeConvert.convertToDto(e)));
		return list;
	}

	@Override
	public Page<Employee> findEmployeebyFilter(Integer pageSize,Integer pageNumber, String employeeFilter) {
		// TODO Auto-generated method stub
		Pageable page=PageRequest.of(pageNumber-1, pageSize);
		return employeeRepo.findAll(employeeSpecification.findEmployeesByFilter(employeeFilter), page);
		
	}

	@Override
	public String generateEmployeeCode() {
		// TODO Auto-generated method stub
		String empCode="NV-";
		Employee empLast=employeeRepo.findTop1ByOrderByCreatedDateDesc();
		System.out.println(empLast.getEmployeeCode());
		int numberCode=Integer.valueOf(empLast.getEmployeeCode().substring(3))+1;
		
		return (empCode+numberCode);
	}

	@Override
	public boolean saveOrUpdate(EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		Optional<Employee> empCheck=Optional.ofNullable(employeeRepo.findByEmployeeCode(employeeDto.getEmployeeCode()));
		try {
				Employee emp= employeeConvert.convertToEntity(employeeDto);
				if(empCheck.isEmpty()) {
					emp.setCreatedDate(LocalDateTime.now());
				}else {
					emp.setUpdatedDate(LocalDateTime.now());
				}
				emp.getDepartment().addEmployee(emp);
				employeeRepo.save(emp);
				return true;
			
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public List<String> validateForm(EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public int deleteEmployeeById(int employId) {
		// TODO Auto-generated method stub
		
		 if( employId!=0) {
			 employeeRepo.deleteById(employId);
			 return 1;
		 }else {
			 return 0;
		 }
		 
	}

	@Override
	public Employee findByEmployeeCode(String empCode) {
		// TODO Auto-generated method stub
		return employeeRepo.findByEmployeeCode(empCode);
	}

	@Override
	public byte[]  exportExcelEmployee(ByteArrayOutputStream  stream, List<EmployeeDto> employees) {
		// TODO Auto-generated method stub
		try(Workbook workbook = new XSSFWorkbook()){
            Sheet sheet =  workbook.createSheet("Employee");
            
            CellStyle cellStyle = workbook.createCellStyle();
            //set title
            

            //set border to table
            cellStyle.setBorderTop(BorderStyle.MEDIUM);
            cellStyle.setBorderRight(BorderStyle.MEDIUM);
            cellStyle.setBorderBottom(BorderStyle.MEDIUM);
            cellStyle.setBorderLeft(BorderStyle.MEDIUM);
            cellStyle.setAlignment(HorizontalAlignment.LEFT);
            
            CellStyle cellStyleValue=workbook.createCellStyle();
            cellStyleValue.setBorderTop(BorderStyle.MEDIUM);
            cellStyleValue.setBorderRight(BorderStyle.MEDIUM);
            cellStyleValue.setBorderBottom(BorderStyle.MEDIUM);
            cellStyleValue.setBorderLeft(BorderStyle.MEDIUM);
            cellStyleValue.setAlignment(HorizontalAlignment.LEFT);
            cellStyleValue.setAlignment(HorizontalAlignment.CENTER);
            cellStyleValue.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
            cellStyleValue.setFillForegroundColor(IndexedColors.AQUA.getIndex());
           
           // set with column
            sheet.setColumnWidth(0, 4000);
            sheet.setColumnWidth(1, 6000);
            sheet.setColumnWidth(2, 6000);
            sheet.setColumnWidth(3, 6000);
            sheet.setColumnWidth(4, 12000);
            sheet.setColumnWidth(5, 6000);
            sheet.setColumnWidth(6, 6000);
            sheet.setColumnWidth(7, 6000);
            sheet.setColumnWidth(8, 4000);
            
            Row row = sheet.createRow(0);
            // Header
            setHeaderSheet(row,0, "Employee Id", cellStyleValue, sheet);
            setHeaderSheet(row,1, "Employee Code", cellStyleValue, sheet);
            setHeaderSheet(row,2, "Employee name", cellStyleValue, sheet);
            setHeaderSheet(row,3, "Position Name", cellStyleValue, sheet);
            setHeaderSheet(row,4, "Address", cellStyleValue, sheet);
            setHeaderSheet(row,5, "Department Name", cellStyleValue, sheet);
            setHeaderSheet(row,6, "Phone Number", cellStyleValue, sheet);
            setHeaderSheet(row,7, "Email", cellStyleValue, sheet);
            setHeaderSheet(row,8, "Status", cellStyleValue, sheet);
            //set color
            
            //Set data
            int rowNum = 1;
            for (EmployeeDto emp : employees) {
                Row empDataRow = sheet.createRow(rowNum++);
                
                this.<Integer>setDataSheet(empDataRow, 0, cellStyle, emp.getId());
                this.<String>setDataSheet(empDataRow, 1, cellStyle, emp.getEmployeeCode());
                this.<String>setDataSheet(empDataRow, 2, cellStyle, emp.getEmployeeName());
                this.<String>setDataSheet(empDataRow, 3, cellStyle, emp.getEmployeePosition());
                this.<String>setDataSheet(empDataRow, 4, cellStyle, emp.getAddress());
                this.<String>setDataSheet(empDataRow, 5, cellStyle, emp.getDepartmentName());
                this.<String>setDataSheet(empDataRow, 6, cellStyle, emp.getPhoneNumber());
                this.<String>setDataSheet(empDataRow, 7, cellStyle, emp.getEmail());
                this.<Boolean>setDataSheet(empDataRow, 8, cellStyle, emp.getStatus());
               
            }

            //write output to stream
            workbook.write(stream);
            workbook.close();
            return stream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
		
	}

	public void setHeaderSheet(Row row,int index,String nameHeader,CellStyle cellStyle,Sheet sheet) {
		
         Cell cell = row.createCell(index);
         cell.setCellValue(nameHeader);
         cell.setCellStyle(cellStyle);
	}
	public <T> void setDataSheet(Row empDataRow,int index,CellStyle cellStyle,T emp) {
		 Optional<T> optCheck=Optional.ofNullable(emp);
         Cell empCell = empDataRow.createCell(index);
         empCell.setCellStyle(cellStyle);
         empCell.setCellValue(optCheck.isEmpty()?" ": emp.toString());
	}
	

}
