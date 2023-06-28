package com.amis.misa.controller.versions.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.amis.misa.annotation.RestApiV1;
import com.amis.misa.entities.AccountEmployee;




@RestApiV1
public class BaseController<T> {
	public boolean isLogined() {
		boolean isLogined = false;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			isLogined = true;
		}
		return isLogined;
	}
	public AccountEmployee getUserLogined() throws Exception {
		Object userLogined = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userLogined != null && userLogined instanceof UserDetails) {
			

			return  (AccountEmployee) userLogined;
		}
		return null;
	}
}
