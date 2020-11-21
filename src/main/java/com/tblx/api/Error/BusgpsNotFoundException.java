package com.tblx.api.Error;

import org.springframework.http.HttpStatus;

public class BusgpsNotFoundException extends BusgpsException{

	private static final HttpStatus STATUSCODE = HttpStatus.NOT_FOUND;
	private static final String ERRORMESSAGE = "Busgps Not Found.";

	public BusgpsNotFoundException() {
		super(ERRORMESSAGE, STATUSCODE);
	}
}
