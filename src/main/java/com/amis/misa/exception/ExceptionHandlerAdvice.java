package com.amis.misa.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;




@RestControllerAdvice
public class ExceptionHandlerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String,String> handelerInvalidAgrument(MethodArgumentNotValidException ex){
		Map<String,String> erMap=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error ->{
			erMap.put(error.getField(), error.getDefaultMessage());
		});
		return erMap;
	}
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleUnwantedException(Exception e) {
	        
	        e.printStackTrace();  
	        return ResponseEntity.status(500).body("Unknow error");
	    }
	 @ExceptionHandler(BindException.class)
	 @ResponseStatus(HttpStatus.BAD_REQUEST)  // Nếu validate fail thì trả về 400
	 public String handleBindException(BindException e) {
	     // Trả về message của lỗi đầu tiên
	     String errorMessage = "";
	     
	     if (e.getBindingResult().hasErrors())
	         errorMessage= e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
	     logger.info(errorMessage);
	     return errorMessage;
	 }
	 @ExceptionHandler(value = { NotFoundException.class,AppException.class,CustomIOException.class})
	 @ResponseStatus(HttpStatus.BAD_REQUEST)  // Nếu validate fail thì trả về 400
	 public String handleException(NotFoundException e) {
	     // Trả về message của lỗi đầu tiên
		 logger.info(e.getUserMessage());
	   return (e.getUserMessage());
	 }
}
