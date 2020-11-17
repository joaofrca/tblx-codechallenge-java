package com.tblx.api.Error;

public class BusgpsNotFoundException extends RuntimeException{

	BusgpsNotFoundException(Long id) {
		super("Could not find Busgps " + id);
	}
}
