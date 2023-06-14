package com.amis.misa.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseDto {
	private Integer id;

	
	private Boolean status = Boolean.TRUE;

	
	private Integer createdBy;

	
	private Integer updatedBy;

	
	private Date createdDate;

	
	private Date updatedDate;
}
