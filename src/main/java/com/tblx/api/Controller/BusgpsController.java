package com.tblx.api.Controller;

import com.tblx.api.Error.BusgpsDateConversionException;
import com.tblx.api.Error.BusgpsExceptionHandler;
import com.tblx.api.Model.Busgps;
import com.tblx.api.Model.VehicleTrace;
import com.tblx.api.Repositories.BusgpsRepository;
import com.tblx.api.Services.BusgpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@Validated
public class BusgpsController {

	@Autowired
	public BusgpsRepository busgpsRepository;

	@Autowired
	public BusgpsService busgpsService;

	@GetMapping(value= "/task1/{startTime}/{endTime}")
	public ResponseEntity getRunningOperators(@PathVariable(value="startTime") @NonNull String startTime,
											@PathVariable(value="endTime") @NonNull String endTime){
		try {
			Set<String> operators = busgpsService.getRunningOperators(startTime, endTime);
			return new ResponseEntity(operators, HttpStatus.OK);
		}
		catch (Exception exception)
		{
			return BusgpsExceptionHandler.handleException(exception);
		}
	}

	@GetMapping(value= "/task2/{startTime}/{endTime}/{operator}")
	public ResponseEntity getVehiclesIDList(@PathVariable(value="startTime") @NonNull String startTime,
										  @PathVariable(value="endTime") @NonNull String endTime,
										  @PathVariable(value="operator") @NonNull String operator){

		try {
			Set<Integer> vehiclesIDList = busgpsService.getVehiclesIDList(startTime, endTime, operator);
			return new ResponseEntity(vehiclesIDList, HttpStatus.OK);
		}
		catch (Exception exception)
		{
			return BusgpsExceptionHandler.handleException(exception);
		}
	}

	@GetMapping(value= "/task3/{startTime}/{endTime}/{operator}")
	public ResponseEntity getVehiclesAtStop(@PathVariable(value="startTime") @NonNull String startTime,
										  @PathVariable(value="endTime") @NonNull String endTime,
										  @PathVariable(value="operator") @NonNull String operator){

		try {
			Set<Integer> vehiclesAtStop = busgpsService.getVehiclesAtStop(startTime, endTime, operator);
			return new ResponseEntity(vehiclesAtStop, HttpStatus.OK);
		}
		catch (Exception exception)
		{
			return BusgpsExceptionHandler.handleException(exception);
		}
	}

	@GetMapping(value= "/task4/{startTime}/{endTime}/{vehicleID}")
	public ResponseEntity getVehicleTrace(@PathVariable(value="startTime") @NonNull String startTime,
										   @PathVariable(value="endTime") @NonNull String endTime,
										   @PathVariable(value="vehicleID") @NonNull int vehicleID){

		try {
			List<VehicleTrace> vehicleTrace = busgpsService.getVehicleTrace(startTime, endTime, vehicleID);
			return new ResponseEntity(vehicleTrace, HttpStatus.OK);
		}
		catch (Exception exception)
		{
			return BusgpsExceptionHandler.handleException(exception);
		}
	}

	//TODO TO BE DELETED!!!//TODO TO BE DELETED!!!//TODO TO BE DELETED!!!//TODO TO BE DELETED!!!//TODO TO BE DELETED!!!

	@GetMapping(value= "/task11/{startTime}/{endTime}")
	public List<Busgps> getRunningOperators2(@PathVariable(value="startTime") @NonNull String startTime,
									  @PathVariable(value="endTime") @NonNull String endTime){

		return busgpsRepository.findByTimestampBetween(Long.parseLong(startTime), Long.parseLong(endTime));
	}

	@GetMapping(value= "/task22/{startTime}/{endTime}/{operator}")
	public List<Busgps> getVehiclesIDList2(@PathVariable(value="startTime") @NonNull String startTime,
									@PathVariable(value="endTime") @NonNull String endTime,
									@PathVariable(value="operator") @NonNull String operator){

		return busgpsRepository.findByTimestampBetweenAndOperator(Long.parseLong(startTime), Long.parseLong(endTime), operator);
	}

	@GetMapping(value= "/task33/{startTime}/{endTime}/{operator}")
	public List<Busgps> getVehiclesAtStop2(@PathVariable(value="startTime") @NonNull String startTime,
									@PathVariable(value="endTime") @NonNull String endTime,
									@PathVariable(value="operator") @NonNull String operator){
		return busgpsRepository.findByTimestampBetweenAndOperator(Long.parseLong(startTime), Long.parseLong(endTime), operator);
	}

	@GetMapping(value= "/task44/{startTime}/{endTime}/{vehicleID}")
	public List<Busgps> getVehicleTrace2(@PathVariable(value="startTime") @NonNull String startTime,
										@PathVariable(value="endTime") @NonNull String endTime,
										@PathVariable(value="vehicleID") @NonNull int vehicleID){
		return busgpsRepository.findByTimestampBetweenAndVehicleID(Long.parseLong(startTime), Long.parseLong(endTime), vehicleID);
	}

}
