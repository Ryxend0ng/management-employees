package com.amis.misa.entities.app;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="position")
public class Position extends BaseEntity{
	@Column(name = "positionCode", nullable = true)
	String positionCode;
	
	@Column(name = "positionName", nullable = true)
	String positionName;
	
	
	

}
