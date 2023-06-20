package com.amis.misa.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;



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
@Table(name ="role")
public class Role extends BaseEntity{
	@Column(name = "roleCode", nullable = true)
	private String roleCode;
	
	@Column(name = "roleName", nullable = true)
	private String roleName;
	
	@Column(name = "description", nullable = true)
	private String description;
	@OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
	List<UserRole> luserRoles=new ArrayList<UserRole>();
	
	public void addUserRole(UserRole user) {
		luserRoles.add(user);
		user.setRole(this);
	}
	public void removeUserRole(UserRole user) {
		luserRoles.remove(user);
		user.setRole(null);
	}
}
