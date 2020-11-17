package com.tblx.api.Controller;

import com.tblx.api.Model.Busgps;
import com.tblx.api.Repositories.BusgpsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping(value= "/task1/:starttime/:endtime")
	public List<Busgps> getRunningOperators(){
//		return busgpsRepository.findAll();
		return busgpsRepository.findAll();
	}

	@GetMapping(value= "/task2/:starttime/:endtime/:operator")
	public List<Busgps> getVehiclesIDList(){
		return busgpsRepository.findAll();
	}

	@GetMapping(value= "/task3/:starttime/:endtime/:operator")
	public List<Busgps> getVehiclesAtStop(){
		return busgpsRepository.findAll();
	}

	@GetMapping(value= "/task4/:starttime/:endtime/:vehicleID")
	public List<Busgps> getVehicleTrace(){
		return busgpsRepository.findAll();
	}

}
