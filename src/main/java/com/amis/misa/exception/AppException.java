package com.amis.misa.exception;

public class AppException extends RuntimeException {
    /**
	 * 
	 */
	private int code;
	private String message;
	private static final long serialVersionUID = 1L;

	public AppException(int code,String message) {
        this.code=code;
        this.message=message;
    }
}
