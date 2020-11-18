package com.tblx.api.Controller;

import com.mongodb.BasicDBObject;
import com.tblx.api.Model.Busgps;
import com.tblx.api.Repositories.BusgpsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BusgpsController {

	@Autowired
	public BusgpsRepository busgpsRepository;

	@GetMapping(value= "/all")
	public List<Busgps> getAllBusgps(){
		return busgpsRepository.findAll();
	}

	@GetMapping(value= "/task5/{operator}")
	public List<Busgps> getMerdasTeste(@PathVariable(value="operator") String operator){

		return busgpsRepository.findByOperator(operator);
	}

	@GetMapping(value= "/task1/{starttime}/{endtime}")
	public List<Busgps> getRunningOperators(@PathVariable(value="starttime") String starttime,
											@PathVariable(value="endtime") String endtime){

		return busgpsRepository.findByTimestampBetween(Long.parseLong(starttime), Long.parseLong(endtime));
	}

	@GetMapping(value= "/task2/{starttime}/{endtime}/{operator}")
	public List<Busgps> getVehiclesIDList(@PathVariable(value="starttime") String starttime,
										  @PathVariable(value="endtime") String endtime,
										  @PathVariable(value="operator") String operator){
		return busgpsRepository.findByTimestampBetweenAndOperator(Long.parseLong(starttime), Long.parseLong(endtime), operator);
	}

	@GetMapping(value= "/task3/{starttime}/{endtime}/{operator}")
	public List<Busgps> getVehiclesAtStop(@PathVariable(value="starttime") String starttime,
										  @PathVariable(value="endtime") String endtime,
										  @PathVariable(value="operator") String operator){
		return busgpsRepository.findByTimestampBetweenAndOperator(Long.parseLong(starttime), Long.parseLong(endtime), operator);
	}

	@GetMapping(value= "/task4/{starttime}/{endtime}/{vehicleID}")
	public List<Busgps> getVehicleTrace(@PathVariable(value="starttime") String starttime,
										@PathVariable(value="endtime") String endtime,
										@PathVariable(value="vehicleID") int vehicleID){
		return busgpsRepository.findByTimestampBetweenAndVehicleID(Long.parseLong(starttime), Long.parseLong(endtime), vehicleID);
	}

}
