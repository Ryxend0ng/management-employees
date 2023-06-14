package com.amis.misa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.amis.misa.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>,JpaSpecificationExecutor<Department>{

}
