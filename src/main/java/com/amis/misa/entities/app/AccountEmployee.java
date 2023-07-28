package com.amis.misa.entities.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="account_employee")
public class AccountEmployee extends BaseEntity implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	

	@Column(name = "username", nullable = true)
	private String username;
	
	@Column(name = "password", nullable = true)
	private String password;
	
	@OneToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	@JsonIgnore
	@OneToMany(mappedBy = "accountEmployee", fetch = FetchType.EAGER)
	private List<UserRole> userRoles=new ArrayList<UserRole>();
	
	public void addUserRole(UserRole user) {
		userRoles.add(user);
		user.setAccountEmployee(this);
	}
	public void removeUserRole(UserRole user) {
		userRoles.remove(user);
		user.setAccountEmployee(null);
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub

		return null; 
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
