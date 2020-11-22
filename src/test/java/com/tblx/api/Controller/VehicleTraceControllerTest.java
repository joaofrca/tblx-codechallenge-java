package com.tblx.api.Controller;

import com.tblx.api.Error.BusgpsDateConversionException;
import com.tblx.api.Error.BusgpsNotFoundException;
import com.tblx.api.Model.VehicleTrace;
import com.tblx.api.Services.VehicleTraceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//adicionar unhappy flows?

@RunWith(MockitoJUnitRunner.class)
@Tag("VehicleTraceControllerTest")
public class VehicleTraceControllerTest {

	@Mock
	VehicleTraceService vehicleTraceService;

	@InjectMocks
	VehicleTraceController vehicleTraceController;

	@Test
	@DisplayName("getVehicleTraceTest")
	public void getVehicleTraceTest() throws BusgpsNotFoundException, BusgpsDateConversionException {

		List<VehicleTrace> vehicleTraceExpected = Stream.of(new VehicleTrace("2012-11-29", "-6.305417", "53.396168"),
				new VehicleTrace("2012-11-29", "-6.306583", "53.398151"))
				.collect(Collectors.toList());
		Mockito
				.when(vehicleTraceService.getVehicleTrace("2012-11-30T00:00:29", "2012-11-30T00:00:51", 40021))
				.thenReturn(vehicleTraceExpected);

		ResponseEntity responseEntityResult = vehicleTraceController.getVehicleTrace("2012-11-30T00:00:29", "2012-11-30T00:00:51", 40021);
		Assert.assertEquals(HttpStatus.OK,responseEntityResult.getStatusCode());
		Assert.assertEquals(vehicleTraceExpected,responseEntityResult.getBody());
	}

}
