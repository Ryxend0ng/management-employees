package com.amis.misa.dto;




import javax.validation.constraints.NotBlank;

import com.amis.misa.constants.UserMessageConstant;
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
public class EmployeeDto extends BaseDto{
	
	
	@NotBlank(message = UserMessageConstant.ERROR_NOT_BLANK)
	private String employeeCode;
	

	private String employeeName;
	
	
	private String employeePosition;
	
	
	private int gender;
	
	private int departmentId;
	
	private String departmentName;
	
	 
	private String dateOfBirth;
	
	
	private String email;
	
	
	private String identityNumber;
	
	
	private String identityDate;
	
	
	private String identityPlace;
	
	
	private String address;
	

	private String telephoneNumber;
	private String phoneNumber;
	
	private String bankAccountNumber;
	
	
	private String bankName;
	
	
	private String branchName;
}
