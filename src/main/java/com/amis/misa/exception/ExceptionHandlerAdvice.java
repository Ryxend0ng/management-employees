package com.amis.misa.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handelerInvalidAgrument(MethodArgumentNotValidException ex){
		Map<String,String> erMap=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error ->{
			erMap.put(error.getField(), error.getDefaultMessage());
		});
		return erMap;
	}
}
