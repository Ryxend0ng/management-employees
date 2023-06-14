package com.amis.misa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name ="employee")
public class Employee extends BaseEntity {
	
	@Column(name = "employeeCode", nullable = false)
	private String employeeCode;
	
	@Column(name = "employeeName", nullable = false)
	private String employeeName;
	
	@Column(name = "employeePosition", nullable = true)
	private String employeePosition;
	
	@Column(name = "gender", nullable = true)
	private Integer gender;
	
	@Column(name = "dateOfBirth", nullable = true)
	private String dateOfBirth;
	
	@Column(name = "email", nullable = true)
	private String email;
	
	@Column(name = "identityNumber", nullable = true)
	private String identityNumber;
	
	@Column(name = "identityDate", nullable = true)
	private String identityDate;
	
	@Column(name = "identityPlace", nullable = true)
	private String identityPlace;
	
	@Column(name = "address", nullable = true)
	private String address;
	
	@Column(name = "telephoneNumber", nullable = true)
	private String telephoneNumber;
	
	@Column(name = "phoneNumber", nullable = true)
	private String phoneNumber;
	
	
	
	@Column(name = "bankAccountNumber", nullable = true)
	private String bankAccountNumber;
	
	@Column(name = "bankName", nullable = true)
	private String bankName;
	
	@Column(name = "branchName", nullable = true)
	private String branchName;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	private Department department;
	
	
}
