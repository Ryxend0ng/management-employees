package com.amis.misa.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class DataInfPageForJson<T> {
	@JsonProperty("TotalPage")
	private int totalPage;
	
	@JsonProperty("TotalRecord")
	private long totalRecord;
	
	@JsonProperty("Data")
	private List<T> data=new ArrayList<>();
	
	
}
