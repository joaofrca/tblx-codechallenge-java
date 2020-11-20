package com.tblx.api.Controller;

import com.tblx.api.Services.BusgpsService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//JSONArray to list error?
//adicionar unhappy flows?

@RunWith(MockitoJUnitRunner.class)

public class BusgpsControllerTest {

	@Mock
	BusgpsService busgpsService;

	@InjectMocks
	BusgpsController busgpsController;

	@Test
	public void getRunningOperatorsTest()
	{
		Set<String> operatorsArray = Stream.of("CD","RD","CF","HN","SL","D1","D2","PO").collect(Collectors.toSet());

		Mockito
				.when(busgpsService.getRunningOperators("1354233629999999", "1354233651000000"))
				.thenReturn(operatorsArray);

		ResponseEntity responseEntity = busgpsController.getRunningOperators("1354233629999999", "1354233651000000");
		Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
		Assert.assertEquals(operatorsArray,responseEntity.getBody());
	}

	@Test
	public void getVehiclesIDListTest()
	{
		Set<Integer> vehiclesID = Stream.of(33223, 40012, 40014, 33167).collect(Collectors.toSet());

		Mockito
				.when(busgpsService.getVehiclesIDList("1354233629999999", "1354233651000000", "HN"))
				.thenReturn(vehiclesID);

		ResponseEntity responseEntity = busgpsController.getVehiclesIDList("1354233629999999", "1354233651000000", "HN");
		Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
		Assert.assertEquals(vehiclesID,responseEntity.getBody());
	}

	@Test
	public void getVehiclesAtStopTest()
	{
		Set<Integer> vehiclesAtStop = Stream.of(40021, 33223, 33513).collect(Collectors.toSet());

		Mockito
				.when(busgpsService.getVehiclesAtStop("1354233629999999", "1354233651000000", "HN"))
				.thenReturn(vehiclesAtStop);

		ResponseEntity responseEntity = busgpsController.getVehiclesAtStop("1354233629999999", "1354233651000000", "HN");
		Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
		Assert.assertEquals(vehiclesAtStop,responseEntity.getBody());
	}

	@Test
	public void getVehicleTraceTest()
	{
		JSONArray vehicleTrace = new JSONArray();
		try {
			JSONObject trace = new JSONObject();
			trace.put("lon", "-6.305417");
			trace.put("lat", "53.396168");
			trace.put("timestamp", "2012-11-29");
			vehicleTrace.put(trace);
		} catch (JSONException e) {
			//falta aqui
		}

		Mockito
				.when(busgpsService.getVehicleTrace("1354233629999999", "1354233651000000", 40021))
				.thenReturn(vehicleTrace);

		ResponseEntity responseEntity = busgpsController.getVehicleTrace("1354233629999999", "1354233651000000", 40021);
		Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
		Assert.assertEquals(vehicleTrace,responseEntity.getBody());
	}

}
