package com.amis.misa.entities.app;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name ="labor_contact")
public class LaborContact extends BaseEntity{
	@Column(name = "typeContact", nullable = true)
	String typeContact;
	
	@Column(name = "fromLaborContact", nullable = true)
	LocalDate fromLaborContact;
	
	@Column(name = "toLaborContact", nullable = true)
	LocalDate toLaborContact;
	
	@OneToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
}
