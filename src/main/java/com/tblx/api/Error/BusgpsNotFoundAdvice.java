package com.tblx.api.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BusgpsNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(BusgpsNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String employeeNotFoundHandler(BusgpsNotFoundException ex) {
		return ex.getMessage();
	}
}
