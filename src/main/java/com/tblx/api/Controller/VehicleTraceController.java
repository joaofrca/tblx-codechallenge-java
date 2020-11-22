package com.tblx.api.Controller;

import com.tblx.api.Error.BusgpsExceptionHandler;
import com.tblx.api.Model.VehicleTrace;
import com.tblx.api.Repositories.BusgpsRepository;
import com.tblx.api.Services.VehicleTraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class VehicleTraceController {

	@Autowired
	public BusgpsRepository busgpsRepository;

	@Autowired
	public VehicleTraceService vehicleTraceService;

	/**
	 * Gets a list of Vehicle Traces for a given time frame and vehicle.
	 * @param startTime in ISO-8601 format.
	 * @param endTime in ISO-8601 format.
	 * @param vehicleID vehicle ID.
	 * @return ResponseEntity
	 */
	@GetMapping(value= "/vehicleTrace/{startTime}/{endTime}/{vehicleID}")
	public ResponseEntity getVehicleTrace(@PathVariable(value="startTime") @NonNull String startTime,
										   @PathVariable(value="endTime") @NonNull String endTime,
										   @PathVariable(value="vehicleID") @NonNull int vehicleID){

		try {
			List<VehicleTrace> vehicleTrace = vehicleTraceService.getVehicleTrace(startTime, endTime, vehicleID);
			return new ResponseEntity(vehicleTrace, HttpStatus.OK);
		}
		catch (Exception exception)
		{
			return BusgpsExceptionHandler.handleException(exception);
		}
	}

}
