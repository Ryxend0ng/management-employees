package com.amis.misa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.amis.misa.entities.AccountEmployee;
import com.amis.misa.entities.Employee;


@Repository
public interface AccountEmployeeRepository extends JpaRepository<AccountEmployee, Integer>,JpaSpecificationExecutor<AccountEmployee>{
	public AccountEmployee findByUsername(String accountName);
}