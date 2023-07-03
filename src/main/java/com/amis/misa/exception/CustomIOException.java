package com.amis.misa.exception;

import java.io.IOException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomIOException extends IOException{
	private int code;
	private String message;
	private static final long serialVersionUID = 1L;

	public CustomIOException(int code,String message) {
        this.code=code;
        this.message=message;
    }
}
