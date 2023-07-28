package com.amis.misa.entities.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
@Table(name ="bonus")
public class Bonus extends BaseEntity{
	@Column(name = "bonusName", nullable = false)
	String bonusName;
	
	@Column(name = "reason", nullable = false)
	String reason;
	
	@Column(name = "content", nullable = false)
	String content;
	
	@Column(name = "bonusTime", nullable = false)
	LocalDate bonusTime;
	
	@OneToMany(mappedBy = "bonus")
	List<BonusEmployee> listBe=new ArrayList<BonusEmployee>();
	public void addBonusEmployee(BonusEmployee be) {
		listBe.add(be);
		be.setBonus(this);
	}
	public void removeBonusEmployee(BonusEmployee be) {
		listBe.remove(be);
		be.setBonus(null);
	}
}
