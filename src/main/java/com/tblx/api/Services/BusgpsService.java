package com.tblx.api.Services;

import com.tblx.api.Error.BusgpsDateConversionException;
import com.tblx.api.Error.BusgpsNotFoundException;
import com.tblx.api.Model.Busgps;
import com.tblx.api.Model.VehicleTrace;
import com.tblx.api.Repositories.BusgpsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BusgpsService {

	@Autowired
	public BusgpsRepository busgpsRepository;

	public Set<String> getRunningOperators(String startTime, String endTime) throws BusgpsNotFoundException,
			BusgpsDateConversionException {

		long microStartTime;
		long microEndTime;
		try {
			microStartTime = getMicroTimestamp(startTime);
			microEndTime = getMicroTimestamp(endTime);
		} catch (BusgpsDateConversionException exception) {
			throw exception;
		}

		List<Busgps> busgpsList = busgpsRepository.findByTimestampBetween(microStartTime, microEndTime);
		if (busgpsList.isEmpty()) throw new BusgpsNotFoundException();

		return busgpsList.stream().map(Busgps::getOperator).collect(Collectors.toSet());
	}

	public Set<Integer> getVehiclesIDList(String startTime, String endTime, String operator) throws BusgpsNotFoundException,
			BusgpsDateConversionException {

		long microStartTime;
		long microEndTime;
		try {
			microStartTime = getMicroTimestamp(startTime);
			microEndTime = getMicroTimestamp(endTime);
		} catch (BusgpsDateConversionException exception) {
			throw exception;
		}

		List<Busgps> busgpsList = busgpsRepository.findByTimestampBetweenAndOperator(microStartTime, microEndTime, operator);
		if (busgpsList.isEmpty()) throw new BusgpsNotFoundException();

		return busgpsList
				.stream()
				.map(Busgps::getVehicleID)
				.map(vehicleID -> Integer.parseInt(vehicleID))
				.collect(Collectors.toSet());
	}

	public Set<Integer> getVehiclesAtStop(String startTime, String endTime, String operator) throws BusgpsNotFoundException,
			BusgpsDateConversionException {

		long microStartTime;
		long microEndTime;
		try {
			microStartTime = getMicroTimestamp(startTime);
			microEndTime = getMicroTimestamp(endTime);
		} catch (BusgpsDateConversionException exception) {
			throw exception;
		}

		List<Busgps> busgpsList = busgpsRepository.findByTimestampBetweenAndOperator(microStartTime, microEndTime, operator);
		if (busgpsList.isEmpty()) throw new BusgpsNotFoundException();

		return busgpsList
				.stream()
				.filter(busgps -> busgps.getAtStop().equals("1"))
				.map(Busgps::getVehicleID)
				.map(vehicleID -> Integer.parseInt(vehicleID))
				.collect(Collectors.toSet());
	}

	public List<VehicleTrace> getVehicleTrace(String startTime, String endTime, int vehicleID) throws BusgpsNotFoundException,
			BusgpsDateConversionException {

		long microStartTime;
		long microEndTime;
		try {
			microStartTime = getMicroTimestamp(startTime);
			microEndTime = getMicroTimestamp(endTime);
		} catch (BusgpsDateConversionException exception) {
			throw exception;
		}

		List<Busgps> busgpsList = busgpsRepository.findByTimestampBetweenAndVehicleID(microStartTime, microEndTime, vehicleID);
		if (busgpsList.isEmpty()) throw new BusgpsNotFoundException();

		return busgpsList
				.stream()
				.sorted(Comparator.comparing(Busgps::getTimeframe))
				.map(busgps -> new VehicleTrace(busgps.getTimeframe(), busgps.getLon(), busgps.getLat()))
				.collect(Collectors.toList());
	}

	public long getMicroTimestamp (String iso8601str) throws BusgpsDateConversionException {
		try {
			long epochMicros = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(iso8601str).getTime()*1000L ;
			return epochMicros;
		} catch (Exception e) {
			throw new BusgpsDateConversionException();
		}
	}

}
