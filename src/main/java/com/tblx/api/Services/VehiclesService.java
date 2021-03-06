package com.tblx.api.Services;

import com.tblx.api.Error.BusgpsDateConversionException;
import com.tblx.api.Error.BusgpsNotFoundException;
import com.tblx.api.Model.Busgps;
import com.tblx.api.Repositories.BusgpsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VehiclesService {

	@Autowired
	public BusgpsRepository busgpsRepository;

	@Autowired
	public UtilsService utilsService;

	/**
	 * Gets a list of Vehicles for a given time frame and operator.
	 * @param startTime in ISO-8601 format.
	 * @param endTime in ISO-8601 format.
	 * @param operator operator ID.
	 * @return list of Vehicles.
	 * @throws BusgpsNotFoundException
	 * @throws BusgpsDateConversionException
	 */
	public Set<Integer> getVehiclesIDList(String startTime, String endTime, String operator) throws BusgpsNotFoundException,
			BusgpsDateConversionException {

		long microStartTime;
		long microEndTime;
		try {
			microStartTime = utilsService.getMicroTimestamp(startTime);
			microEndTime = utilsService.getMicroTimestamp(endTime);
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

	/**
	 * Gets a list of Vehicles at stop for a given time frame and operator.
	 * @param startTime in ISO-8601 format.
	 * @param endTime in ISO-8601 format.
	 * @param operator operator ID.
	 * @return list of Vehicles at stop.
	 * @throws BusgpsNotFoundException
	 * @throws BusgpsDateConversionException
	 */
	public Set<Integer> getVehiclesAtStop(String startTime, String endTime, String operator) throws BusgpsNotFoundException,
			BusgpsDateConversionException {

		long microStartTime;
		long microEndTime;
		try {
			microStartTime = utilsService.getMicroTimestamp(startTime);
			microEndTime = utilsService.getMicroTimestamp(endTime);
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

}
