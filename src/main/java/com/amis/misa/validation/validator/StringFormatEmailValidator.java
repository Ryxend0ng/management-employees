package com.amis.misa.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.amis.misa.validation.StringFormatEmail;

public class StringFormatEmailValidator implements ConstraintValidator<StringFormatEmail, String> {

	private static final String REGEX_FORMAT="\"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$";
	@Override
	public boolean isValid(String emailEmployee, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		  if (StringUtils.isNotEmpty(emailEmployee)) {
		      return emailEmployee.matches(REGEX_FORMAT);
		    }

		    return false;
		
	}
}
