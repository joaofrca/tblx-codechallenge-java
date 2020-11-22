package com.tblx.api.Services;

import com.tblx.api.Error.BusgpsDateConversionException;
import com.tblx.api.Error.BusgpsNotFoundException;
import com.tblx.api.Model.Busgps;
import com.tblx.api.Model.VehicleTrace;
import com.tblx.api.Repositories.BusgpsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleTraceService {

	@Autowired
	public BusgpsRepository busgpsRepository;

	@Autowired
	public UtilsService utilsService;

	/**
	 * Gets a list of Vehicle Traces for a given time frame and vehicle.
	 * @param startTime in ISO-8601 format.
	 * @param endTime in ISO-8601 format.
	 * @param vehicleID vehicle ID.
	 * @return list of Vehicle Traces.
	 * @throws BusgpsNotFoundException
	 * @throws BusgpsDateConversionException
	 */
	public List<VehicleTrace> getVehicleTrace(String startTime, String endTime, int vehicleID) throws BusgpsNotFoundException,
			BusgpsDateConversionException {

		long microStartTime;
		long microEndTime;
		try {
			microStartTime = utilsService.getMicroTimestamp(startTime);
			microEndTime = utilsService.getMicroTimestamp(endTime);
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

}
