package com.amis.misa.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeException extends Exception{

	/**
	 * 
	 */
	private String userMessage;
	private String devMessage;
	private static final long serialVersionUID = 1L;
	public EmployeeException(String message) {
		this.userMessage=message;
	}
	public EmployeeException(String message,String dev) {
		this.userMessage=message;
		this.devMessage=dev;
	}

}
