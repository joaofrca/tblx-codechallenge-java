package com.tblx.api.Services;

import com.tblx.api.Model.Busgps;
import com.tblx.api.Repositories.BusgpsRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BusgpsService {

	@Autowired
	public BusgpsRepository busgpsRepository;

//	@Autowired
//	public BusgpsNotFoundException busgpsNotFoundException;

	//A retornar lista de strings - ok
	public Set<String> getRunningOperators(String startTime, String endTime){
		//validate params
		List<Busgps> busgpsList = busgpsRepository.findByTimestampBetween(Long.parseLong(startTime), Long.parseLong(endTime));
		if (busgpsList.isEmpty()) {return null;} //throw new BusgpsNotFoundException(12);


		return busgpsList.stream().map(Busgps::getOperator).collect(Collectors.toSet());
	}

	//A retornar lista de ints - ok
	public Set<Integer> getVehiclesIDList(String startTime, String endTime, String operator){
		//validate params
		List<Busgps> busgpsList = busgpsRepository.findByTimestampBetweenAndOperator(Long.parseLong(startTime), Long.parseLong(endTime), operator);
		if (busgpsList.isEmpty()) {return null;} //throw new BusgpsNotFoundException(12);

		return busgpsList
				.stream()
				.map(Busgps::getVehicleID)
				.map(vehicleID -> {
					return Integer.parseInt(vehicleID);
				})
				.collect(Collectors.toSet());
	}

	//A retornar lista de ints - ok
	public Set<Integer> getVehiclesAtStop(String startTime, String endTime, String operator){
		//validate params
		List<Busgps> busgpsList = busgpsRepository.findByTimestampBetweenAndOperator(Long.parseLong(startTime), Long.parseLong(endTime), operator);
		if (busgpsList.isEmpty()) {return null;} //throw new BusgpsNotFoundException(12);

		return busgpsList
				.stream()
				.filter(busgps -> busgps.getAtStop().equals("1"))
				.map(Busgps::getVehicleID)
				.map(vehicleID -> {
					return Integer.parseInt(vehicleID);
				})
				.collect(Collectors.toSet());
	}

	//ainda errado. talvez pensar em opção secudária, tipo retornar uma string mais normal
	public JSONArray getVehicleTrace(String startTime, String endTime, int vehicleID){
		//validate params
		List<Busgps> busgpsList = busgpsRepository.findByTimestampBetweenAndVehicleID(Long.parseLong(startTime), Long.parseLong(endTime), vehicleID);
		if (busgpsList.isEmpty()) {return null;} //throw new BusgpsNotFoundException(12);

		JSONArray jarr = new JSONArray();
		busgpsList
				.stream()
				.sorted(Comparator.comparing(Busgps::getTimeframe))
				.map(busgps -> {
					JSONObject jo = new JSONObject();
					try {
						jo.put("timestamp", busgps.getTimeframe());
						jo.put("lon", busgps.getLon());
						jo.put("lat", busgps.getLat());
						jarr.put(jo);
					}
					catch (JSONException e) {
						//falta aqui
					}
					return jo.toString();


				})
				.collect(Collectors.toSet());
		return jarr;
	}

}
