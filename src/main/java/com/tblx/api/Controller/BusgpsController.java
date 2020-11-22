package com.tblx.api.Controller;

import com.tblx.api.Model.Busgps;
import com.tblx.api.Repositories.BusgpsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//TODO TO BE DELETED!!!//TODO TO BE DELETED!!!//TODO TO BE DELETED!!!//TODO TO BE DELETED!!!//TODO TO BE DELETED!!!
@RestController
@Validated
public class BusgpsController {

	@Autowired
	public BusgpsRepository busgpsRepository;

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
