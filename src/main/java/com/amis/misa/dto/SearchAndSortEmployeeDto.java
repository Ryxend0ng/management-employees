package com.amis.misa.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SearchAndSortEmployeeDto {
	@Nullable
	private String employeeCode;
	
	@Nullable
	private String departmentName;
	@Nullable
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime beginDate;
	@Nullable
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endDate;
	@Nullable
	private String positionName;
	@Nullable
	private Boolean status;
}
