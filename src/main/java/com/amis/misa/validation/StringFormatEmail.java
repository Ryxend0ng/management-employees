package com.amis.misa.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.amis.misa.validation.validator.StringFormatEmailValidator;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = StringFormatEmailValidator.class)
public @interface  StringFormatEmail {
	 String message() default "Invalid Limit of Code";
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};

}
