package com.tblx.api.Controller;

import com.tblx.api.Error.BusgpsExceptionHandler;
import com.tblx.api.Repositories.BusgpsRepository;
import com.tblx.api.Services.VehiclesService;
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
public class VehiclesController {

	@Autowired
	public BusgpsRepository busgpsRepository;

	@Autowired
	public VehiclesService vehiclesService;

	/**
	 * Gets a list of Vehicles at stop for a given time frame and operator.
	 * @param startTime in ISO-8601 format.
	 * @param endTime in ISO-8601 format.
	 * @param operator operator ID.
	 * @return ResponseEntity
	 */
	@GetMapping(value= "/vehicles/{startTime}/{endTime}/{operator}")
	public ResponseEntity getVehiclesIDList(@PathVariable(value="startTime") @NonNull String startTime,
										  @PathVariable(value="endTime") @NonNull String endTime,
										  @PathVariable(value="operator") @NonNull String operator){

		try {
			Set<Integer> vehiclesIDList = vehiclesService.getVehiclesIDList(startTime, endTime, operator);
			return new ResponseEntity(vehiclesIDList, HttpStatus.OK);
		}
		catch (Exception exception)
		{
			return BusgpsExceptionHandler.handleException(exception);
		}
	}

	/**
	 * Gets a list of Vehicles at stop for a given time frame and operator.
	 * @param startTime in ISO-8601 format.
	 * @param endTime in ISO-8601 format.
	 * @param operator operator ID.
	 * @return ResponseEntity
	 */
	@GetMapping(value= "/vehiclesAtStop/{startTime}/{endTime}/{operator}")
	public ResponseEntity getVehiclesAtStop(@PathVariable(value="startTime") @NonNull String startTime,
										  @PathVariable(value="endTime") @NonNull String endTime,
										  @PathVariable(value="operator") @NonNull String operator){

		try {
			Set<Integer> vehiclesAtStop = vehiclesService.getVehiclesAtStop(startTime, endTime, operator);
			return new ResponseEntity(vehiclesAtStop, HttpStatus.OK);
		}
		catch (Exception exception)
		{
			return BusgpsExceptionHandler.handleException(exception);
		}
	}
}
