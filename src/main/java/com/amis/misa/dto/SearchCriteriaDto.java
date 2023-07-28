package com.amis.misa.dto;

import com.amis.misa.enums.SearchCriteriaEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchCriteriaDto {
	private String column;
	private String value;
	private Operation operation;
	public enum Operation{
		EQUAL,LIKE,IN
	}
	
	public SearchCriteriaDto(String column,String value) {
		this.column=column;
		this.value=value;
	}
}
