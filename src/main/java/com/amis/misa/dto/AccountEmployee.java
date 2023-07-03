package com.amis.misa.dto;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.amis.misa.constants.UserMessageConstant;

import lombok.Data;
@Data
public class AccountEmployee extends BaseDto{
	@NotEmpty(message = UserMessageConstant.ERROR_NOT_BLANK_USERNAME)
    private String username;
	
	@NotEmpty(message =UserMessageConstant.ERROR_NOT_BLANK_PASSWORD)
	@Min(value = 8, message = UserMessageConstant.PASSWORD_HAS_MIN_8)
	private String password;
}
