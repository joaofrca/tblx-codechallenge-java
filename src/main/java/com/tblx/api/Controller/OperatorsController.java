package com.tblx.api.Controller;

import com.tblx.api.Error.BusgpsExceptionHandler;
import com.tblx.api.Repositories.BusgpsRepository;
import com.tblx.api.Services.OperatorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@Validated
public class OperatorsController {

	@Autowired
	public BusgpsRepository busgpsRepository;

	@Autowired
	public OperatorsService operatorsService;

	/**
	 * Gets a list of Running Operators for a given time frame.
	 * @param startTime in ISO-8601 format.
	 * @param endTime in ISO-8601 format.
	 * @return ResponseEntity
	 */
	@GetMapping(value= "/operators/{startTime}/{endTime}")
	public ResponseEntity getRunningOperators(@PathVariable(value="startTime") @NonNull String startTime,
											@PathVariable(value="endTime") @NonNull String endTime){
		try {
			Set<String> operators = operatorsService.getRunningOperators(startTime, endTime);
			return new ResponseEntity(operators, HttpStatus.OK);
		}
		catch (Exception exception)
		{
			return BusgpsExceptionHandler.handleException(exception);
		}
	}

}
