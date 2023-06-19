package com.amis.misa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;

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

public class UserRole extends BaseEntity implements GrantedAuthority{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "roleCode", nullable = true)
	private String roleCode;
	
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "account_employee_id")
	private AccountEmployee accountEmployee;
	
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role role;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return roleCode;
	}
}
