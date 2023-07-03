package com.amis.misa.services.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amis.misa.constants.DevMessageConstant;
import com.amis.misa.entities.AccountEmployee;
import com.amis.misa.entities.Employee;
import com.amis.misa.exception.NotFoundException;
import com.amis.misa.repositories.AccountEmployeeRepository;
import com.amis.misa.services.IEmployeeService;


@Service
public class CustomUserDetailsService implements UserDetailsService{


	@Autowired
	AccountEmployeeRepository accRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<AccountEmployee> acc=Optional.ofNullable( accRepo.findByUsername(username));
		if(acc.isEmpty()) {
			throw new NotFoundException(DevMessageConstant.ERROR_NOT_FOUND_IN_DB);
		}else {
		
		return acc.get();
		}
	}
	
}
