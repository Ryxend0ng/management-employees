package com.amis.misa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amis.misa.entities.app.AccountEmployee;
import com.amis.misa.entities.app.Employee;


@Repository(value = "accountRepo")
public interface AccountEmployeeRepository extends BaseRepository<AccountEmployee, Integer>{
	
	public AccountEmployee findByUsername(String accountName);
	
	public AccountEmployee findByUsernameAndPassword(String username,String password);
}
