package com.tblx.api.Controller;

import com.mongodb.BasicDBObject;
import com.tblx.api.Model.Busgps;
import com.tblx.api.Repositories.BusgpsRepository;
import com.tblx.api.Services.BusgpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
	public List<Busgps> getMerdasTeste(@PathVariable(value="operator") String operator){

		return busgpsRepository.findByOperator(operator);
	}

	@GetMapping(value= "/task1/{startTime}/{endTime}")
	public List<Busgps> getRunningOperators(@PathVariable(value="startTime") String startTime,
											@PathVariable(value="endTime") String endTime){

		return busgpsService.getRunningOperators(startTime, endTime);
	}

	@GetMapping(value= "/task2/{startTime}/{endTime}/{operator}")
	public List<Busgps> getVehiclesIDList(@PathVariable(value="startTime") String startTime,
										  @PathVariable(value="endTime") String endTime,
										  @PathVariable(value="operator") String operator){

		return busgpsService.getVehiclesIDList(startTime, endTime, operator);
	}

	@GetMapping(value= "/task3/{startTime}/{endTime}/{operator}")
	public List<Busgps> getVehiclesAtStop(@PathVariable(value="startTime") String startTime,
										  @PathVariable(value="endTime") String endTime,
										  @PathVariable(value="operator") String operator){
		return busgpsService.getVehiclesAtStop(startTime, endTime, operator);
	}

	@GetMapping(value= "/task4/{startTime}/{endTime}/{vehicleID}")
	public List<Busgps> getVehicleTrace(@PathVariable(value="startTime") String startTime,
										@PathVariable(value="endTime") String endTime,
										@PathVariable(value="vehicleID") int vehicleID){
		return busgpsService.getVehicleTrace(startTime, endTime, vehicleID);
	}

}
