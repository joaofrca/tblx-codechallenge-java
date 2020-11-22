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
public class OperatorsService {

	@Autowired
	public BusgpsRepository busgpsRepository;

	@Autowired
	public UtilsService utilsService;

	/**
	 * Gets a list of Running Operators for a given time frame.
	 * @param startTime in ISO-8601 format.
	 * @param endTime in ISO-8601 format.
	 * @return list of Running Operators
	 * @throws BusgpsNotFoundException
	 * @throws BusgpsDateConversionException
	 */
	public Set<String> getRunningOperators(String startTime, String endTime) throws BusgpsNotFoundException,
			BusgpsDateConversionException {

		long microStartTime;
		long microEndTime;
		try {
			microStartTime = utilsService.getMicroTimestamp(startTime);
			microEndTime = utilsService.getMicroTimestamp(endTime);
		} catch (BusgpsDateConversionException exception) {
			throw exception;
		}

		List<Busgps> busgpsList = busgpsRepository.findByTimestampBetween(microStartTime, microEndTime);
		if (busgpsList.isEmpty()) throw new BusgpsNotFoundException();

		return busgpsList.stream().map(Busgps::getOperator).collect(Collectors.toSet());
	}

}
