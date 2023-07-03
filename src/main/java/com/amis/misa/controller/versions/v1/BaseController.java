package com.amis.misa.controller.versions.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.amis.misa.annotation.RestApiV1;
import com.amis.misa.constants.UserMessageConstant;
import com.amis.misa.converter.ObjectConvert;
import com.amis.misa.dto.BaseDto;
import com.amis.misa.dto.DataInfPageForJson;
import com.amis.misa.dto.EmployeeDto;
import com.amis.misa.dto.JsonForErrorMessage;
import com.amis.misa.entities.AccountEmployee;
import com.amis.misa.entities.BaseEntity;
import com.amis.misa.entities.Employee;




@RestApiV1
public class BaseController<T extends BaseEntity,K extends BaseDto> {
	
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
	public ResponseEntity<?> resError(String devMess,String userMess){
		JsonForErrorMessage json=new JsonForErrorMessage(devMess,  userMess);
		return ResponseEntity.badRequest().body(json);
	}
	public ResponseEntity<?> resListData(List<K> list,Integer totalPage,Long totalElements){
		
		DataInfPageForJson<K> data=new DataInfPageForJson<K>(totalPage, totalElements, list);
		return ResponseEntity.ok(data);
	}
}
