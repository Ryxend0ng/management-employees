package com.amis.misa.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.amis.misa.constants.UserMessageConstant;
import com.amis.misa.validation.StringFormatEmail;
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
	@StringFormatEmail
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
	
	@OneToMany(mappedBy = "employee")
	public List<BonusEmployee> listBe=new ArrayList<BonusEmployee>();
	
	public void addBonusEmployee(BonusEmployee be) {
		listBe.add(be);
		be.setEmployee(this);
	}
	public void removeBonusEmployee(BonusEmployee be) {
		listBe.remove(be);
		be.setEmployee(null);
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "position_id")
	private Position position;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "academicLevel_id")
	private AcademicLevel academicLevel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "overtime_id")
	private Overtime overTime;
	
	@OneToMany(mappedBy = "employee")
	public List<TimeKeeping> listTimeKeep=new ArrayList<TimeKeeping>();
	
	public void addTimeKeeping(TimeKeeping tk) {
		listTimeKeep.add(tk);
		tk.setEmployee(this);
	}
	public void removeTimeKeeping(TimeKeeping tk) {
		listTimeKeep.remove(tk);
		tk.setEmployee(null);
	}
}
