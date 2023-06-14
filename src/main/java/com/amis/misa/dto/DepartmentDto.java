package com.amis.misa.dto;



import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)// tra ve toan bo key viet hoa
public class DepartmentDto extends BaseDto{
	
	private String departmentName;
	
	private Integer departmentId=super.getId();
	private String departmentCode;
}
