package com.tblx.api.Controller;

import com.tblx.api.Error.BusgpsDateConversionException;
import com.tblx.api.Error.BusgpsNotFoundException;
import com.tblx.api.Services.VehiclesService;
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

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
@Tag("VehiclesControllerTest")
public class VehiclesControllerTest {

	@Mock
	VehiclesService vehiclesService;

	@InjectMocks
	VehiclesController vehiclesController;

	@Test
	@DisplayName("getVehiclesIDListTest")
	public void getVehiclesIDListTest() throws BusgpsNotFoundException, BusgpsDateConversionException {
		Set<Integer> vehiclesIDExpected = Stream.of(33223, 40012, 40014, 33167).collect(Collectors.toSet());

		Mockito
				.when(vehiclesService.getVehiclesIDList("2012-11-30T00:00:29", "2012-11-30T00:00:51", "HN"))
				.thenReturn(vehiclesIDExpected);

		ResponseEntity responseEntityResult = vehiclesController.getVehiclesIDList("2012-11-30T00:00:29", "2012-11-30T00:00:51", "HN");
		Assert.assertEquals(HttpStatus.OK,responseEntityResult.getStatusCode());
		Assert.assertEquals(vehiclesIDExpected,responseEntityResult.getBody());
	}

	@Test
	@DisplayName("getVehiclesAtStopTest")
	public void getVehiclesAtStopTest() throws BusgpsNotFoundException, BusgpsDateConversionException {
		Set<Integer> vehiclesAtStopExpected = Stream.of(40021, 33223, 33513).collect(Collectors.toSet());

		Mockito
				.when(vehiclesService.getVehiclesAtStop("2012-11-30T00:00:29", "2012-11-30T00:00:51", "HN"))
				.thenReturn(vehiclesAtStopExpected);

		ResponseEntity responseEntityResult = vehiclesController.getVehiclesAtStop("2012-11-30T00:00:29", "2012-11-30T00:00:51", "HN");
		Assert.assertEquals(HttpStatus.OK,responseEntityResult.getStatusCode());
		Assert.assertEquals(vehiclesAtStopExpected,responseEntityResult.getBody());
	}

}
