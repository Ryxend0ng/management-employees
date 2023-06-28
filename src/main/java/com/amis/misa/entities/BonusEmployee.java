package com.amis.misa.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class BonusEmployee extends BaseEntity{
	
	@Column(name = "bonusName", nullable = true)
	String bonusName;
	
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "bonus_id")
	private Bonus bonus;
	
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;
}
