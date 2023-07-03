package com.amis.misa.dto;

import com.amis.misa.enums.SearchCriteriaEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
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
}
