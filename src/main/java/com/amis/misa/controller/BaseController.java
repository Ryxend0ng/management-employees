package com.amis.misa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



public class BaseController<T> {
	  public ResponseEntity<?> resSuccess(T data) {
	        Map<String, T> map = new HashMap<>();
	        map.put("data", data);
	        return ResponseEntity.status(HttpStatus.OK).body(map);
	        
	    }
	  public ResponseEntity<?> resListSuccess(List<?> data) {
	        Map<String, List<?>> map = new HashMap<>();
	        map.put("data", data);
	        return ResponseEntity.status(HttpStatus.OK).body(map);
	        
	    }
}
