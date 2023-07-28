package com.amis.misa.constants;

public class UrlConstant {
	// url for employees
	 public static final String PREFIX_EMPLOYEES = "/Employees";
	 public static final String GET_EMPLOYEE_DATA = PREFIX_EMPLOYEES+"/filter";
	 public static final String GET_USER_ID_DATA = PREFIX_EMPLOYEES + "/{id}";
	 public static final String CREATE_EMPLOYEE = PREFIX_EMPLOYEES+"/insert";
	 public static final String UPDATE_EMPLOYEE = PREFIX_EMPLOYEES+"/update";
	 public static final String GET_NEW_EMPLOYEE_CODE = PREFIX_EMPLOYEES+"/NewEmployeeCode";
	 public static final String DELETE_BY_EMPLOYEE_ID = PREFIX_EMPLOYEES+"/{employeeId}";
	 public static final String EXPORT_EXCEL = PREFIX_EMPLOYEES+"/exportExcel";
	 public static final String IMPORT_EXCEL = PREFIX_EMPLOYEES+"/importExcel";
	 public static final String SEARCH_AND_SORT_EMPLOYEEE=PREFIX_EMPLOYEES+"/search_sort";
	 public static final String QR_CODE=PREFIX_EMPLOYEES+"/qr-code";
	 public static final String SCAN_QR_CODE=PREFIX_EMPLOYEES+"/scan/qr-code";
	 
	 //url for department
	 public static final String PREFIX_DEPARTMENTS = "/Departments";
	 public static final String GET_DEPARTMENTS_DATA = PREFIX_DEPARTMENTS;
	 
	 // url for accountEmployee
	 public static final String PREFIX_ACCOUNT_EMPLOYEE = "/Account";
	 public static final String PERFORM_LOGIN = "/perform_login";
}
