package com.amis.misa.entities.app;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "status", nullable = true)
	private Boolean status = Boolean.TRUE;

	@Column(name = "created_by", nullable = true)
	private Integer createdBy;

	@Column(name = "updated_by", nullable = true)
	private Integer updatedBy;

	
	@Column(name = "created_date", nullable = true)
	@CreatedDate
	private LocalDateTime  createdDate;

	@Column(name = "updated_date", nullable = true)
	@CreatedDate
	private LocalDateTime  updatedDate;

	
	
}
