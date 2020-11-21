package com.tblx.api.Controller;

import com.tblx.api.Error.BusgpsDateConversionException;
import com.tblx.api.Error.BusgpsNotFoundException;
import com.tblx.api.Model.VehicleTrace;
import com.tblx.api.Services.BusgpsService;
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
	//	MockMvc mockMvc;
//
//	@Before
//	public void init()
//	{
//		mockMvc = standaloneSetup(busgpsController).build();
//	}
//
//	//novo teste nao funciona tao bem
//	@Test
//	public void getRunningOperatorsTest() throws Exception {
//		Set<String> operatorsArray = Stream.of("CD","RD","CF","HN","SL","D1","D2","PO").collect(Collectors.toSet());
//
//		Mockito
//				.when(busgpsService.getRunningOperators("1354233629999999", "1354233651000000"))
//				.thenReturn(operatorsArray);
//
//		mockMvc.perform(
//				MockMvcRequestBuilders.get("/task1/1354233629999999/1354233651000000/")
//		)
//				.andExpect(status().isOk())
//				.andExpect(content().string(operatorsArray.toString()));
//
////		ResponseEntity responseEntity = busgpsController.getRunningOperators("1354233629999999", "1354233651000000");
////		Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
////		Assert.assertEquals(operatorsArray,responseEntity.getBody());
//	}

	@Test
	public void getRunningOperatorsTest() throws Exception {
		Set<String> operatorsArray = Stream.of("CD","RD","CF","HN","SL","D1","D2","PO").collect(Collectors.toSet());

		Mockito
				.when(busgpsService.getRunningOperators("2012-11-30T00:00:29", "2012-11-30T00:00:51"))
				.thenReturn(operatorsArray);

		ResponseEntity responseEntity = busgpsController.getRunningOperators("2012-11-30T00:00:29", "2012-11-30T00:00:51");
		Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
		Assert.assertEquals(operatorsArray,responseEntity.getBody());
	}

	@Test
	public void getVehiclesIDListTest() throws BusgpsNotFoundException, BusgpsDateConversionException {
		Set<Integer> vehiclesID = Stream.of(33223, 40012, 40014, 33167).collect(Collectors.toSet());

//		2012-11-30T00:00:29.999
//		2012-11-30T00:00:51.999
		Mockito
				.when(busgpsService.getVehiclesIDList("2012-11-30T00:00:29", "2012-11-30T00:00:51", "HN"))
				.thenReturn(vehiclesID);

		ResponseEntity responseEntity = busgpsController.getVehiclesIDList("2012-11-30T00:00:29", "2012-11-30T00:00:51", "HN");
		Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
		Assert.assertEquals(vehiclesID,responseEntity.getBody());
	}

	@Test
	public void getVehiclesAtStopTest() throws BusgpsNotFoundException, BusgpsDateConversionException {
		Set<Integer> vehiclesAtStop = Stream.of(40021, 33223, 33513).collect(Collectors.toSet());

		Mockito
				.when(busgpsService.getVehiclesAtStop("2012-11-30T00:00:29", "2012-11-30T00:00:51", "HN"))
				.thenReturn(vehiclesAtStop);

		ResponseEntity responseEntity = busgpsController.getVehiclesAtStop("2012-11-30T00:00:29", "2012-11-30T00:00:51", "HN");
		Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
		Assert.assertEquals(vehiclesAtStop,responseEntity.getBody());
	}

	@Test
	public void getVehicleTraceTest() throws BusgpsNotFoundException, BusgpsDateConversionException {

		List<VehicleTrace> vehicleTrace = Stream.of(new VehicleTrace("2012-11-29", "-6.305417", "53.396168"),
				new VehicleTrace("2012-11-29", "-6.306583", "53.398151"))
				.collect(Collectors.toList());
		Mockito
				.when(busgpsService.getVehicleTrace("2012-11-30T00:00:29", "2012-11-30T00:00:51", 40021))
				.thenReturn(vehicleTrace);

		ResponseEntity responseEntity = busgpsController.getVehicleTrace("2012-11-30T00:00:29", "2012-11-30T00:00:51", 40021);
		Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
		Assert.assertEquals(vehicleTrace,responseEntity.getBody());
	}

}
