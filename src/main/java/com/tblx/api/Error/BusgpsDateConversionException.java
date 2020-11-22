package com.tblx.api.Error;

import org.springframework.http.HttpStatus;

/**
 * Custom BusgpsDateConversionException.
 */
public class BusgpsDateConversionException extends BusgpsException{

	private static final HttpStatus STATUSCODE = HttpStatus.BAD_REQUEST;
	private static final String ERRORMESSAGE = "Date conversion error. Bad Request.";

	public BusgpsDateConversionException() {
		super(ERRORMESSAGE, STATUSCODE);
	}
}
