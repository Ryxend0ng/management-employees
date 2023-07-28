package com.amis.misa.entities.app;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name ="salary")
public class Salary extends BaseEntity{
	@Column(name = "basicSalary", nullable = true)
	double basicSalary;
	
	@Column(name = "allowance", nullable = true)
	double allowance;
	
	
}
