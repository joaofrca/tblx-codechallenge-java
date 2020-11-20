package com.tblx.api.Controller;

import com.tblx.api.Model.Busgps;
import com.tblx.api.Repositories.BusgpsRepository;
import com.tblx.api.Services.BusgpsService;
import org.json.JSONArray;
import org.json.JSONObject;
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

	//TODO: TO BE DELETED
	@GetMapping(value= "/all")
	public List<Busgps> getAllBusgps(){
		return busgpsRepository.findAll();
	}

	//TODO: TO BE DELETED
	@GetMapping(value= "/task5/{operator}")
	public List<Busgps> getTeste(@PathVariable(value="operator") String operator){

		return busgpsRepository.findByOperator(operator);
	}

	//TODO: TO BE DELETED
	@GetMapping(value= "/teste/{xx}")
	public ResponseEntity getTeste2(@PathVariable(value="xx") String xx){
		JSONObject jo = new JSONObject();
			jo.put("timestamp", "timestamp2");
			jo.put("lon", "lon2");
			jo.put("lat", "lat2");
		return new ResponseEntity(jo, HttpStatus.OK);
	}

	@GetMapping(value= "/task1/{startTime}/{endTime}")
	public ResponseEntity getRunningOperators(@PathVariable(value="startTime") @NonNull String startTime,
											@PathVariable(value="endTime") @NonNull String endTime){

		//validar os nao nulls
		try {
			Set<String> operators = busgpsService.getRunningOperators(startTime, endTime);
			return new ResponseEntity(operators, HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity(e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
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
		catch (Exception e)
		{
			return new ResponseEntity(e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
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
		catch (Exception e)
		{
			return new ResponseEntity(e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value= "/task4/{startTime}/{endTime}/{vehicleID}")
	public ResponseEntity getVehicleTrace(@PathVariable(value="startTime") @NonNull String startTime,
										   @PathVariable(value="endTime") @NonNull String endTime,
										   @PathVariable(value="vehicleID") @NonNull int vehicleID){

		try {
			JSONArray vehicleTrace = busgpsService.getVehicleTrace(startTime, endTime, vehicleID);
			return new ResponseEntity(vehicleTrace.toList(), HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity(e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public String createResponse(String serviceResponse){
		return "{ result:" +  serviceResponse + ", statusCode: OK }";
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
