package com.tblx.api.Services;

import com.tblx.api.Model.Busgps;
import com.tblx.api.Repositories.BusgpsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BusgpsService {

	@Autowired
	public BusgpsRepository busgpsRepository;

	public List<Busgps> getRunningOperators(String startTime, String endTime){

		return busgpsRepository.findByTimestampBetween(Long.parseLong(startTime), Long.parseLong(endTime));
	}

	public List<Busgps> getVehiclesIDList(String startTime, String endTime, String operator){

		return busgpsRepository.findByTimestampBetweenAndOperator(Long.parseLong(startTime), Long.parseLong(endTime), operator);
	}

	public List<Busgps> getVehiclesAtStop(String startTime, String endTime, String operator){

		return busgpsRepository.findByTimestampBetweenAndOperator(Long.parseLong(startTime), Long.parseLong(endTime), operator);
	}

	public List<Busgps> getVehicleTrace(String startTime, String endTime, int vehicleID){

		return busgpsRepository.findByTimestampBetweenAndVehicleID(Long.parseLong(startTime), Long.parseLong(endTime), vehicleID);
	}

}
