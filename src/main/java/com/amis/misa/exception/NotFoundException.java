package com.amis.misa.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private String userMessage;
	private String devMessage;
	private static final long serialVersionUID = 1L;
	public NotFoundException(String message) {
		this.userMessage=message;
	}
	public NotFoundException(String message,String dev) {
		this.userMessage=message;
		this.devMessage=dev;
	}

}
