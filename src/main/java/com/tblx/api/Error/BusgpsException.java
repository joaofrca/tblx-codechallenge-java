package com.tblx.api.Error;

import org.springframework.http.HttpStatus;

/**
 * Custom BusgpsException.
 */
public class BusgpsException extends Exception{

	private final HttpStatus statusCode;
	private final String errorMessage;

	public BusgpsException(String errorMessage, HttpStatus statusCode) {
		super(errorMessage);
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
