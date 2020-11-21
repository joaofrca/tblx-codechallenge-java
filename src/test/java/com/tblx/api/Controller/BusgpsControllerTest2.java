package com.tblx.api.Controller;

import com.tblx.api.Error.BusgpsDateConversionException;
import com.tblx.api.Error.BusgpsNotFoundException;
import com.tblx.api.Model.VehicleTrace;
import com.tblx.api.Services.BusgpsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//adicionar unhappy flows?

@RunWith(MockitoJUnitRunner.class)

public class BusgpsControllerTest2 {

	@Mock
	BusgpsService busgpsService;

	@InjectMocks
	BusgpsController busgpsController;

	MockMvc mockMvc;

	@Before
	public void init()
	{
		mockMvc = standaloneSetup(busgpsController).build();
	}

	//novo teste nao funciona tao bem
	@Test
	public void getRunningOperatorsTest() throws Exception {
		Set<String> operatorsArray = Stream.of("CD","RD","CF","HN","SL","D1","D2","PO").collect(Collectors.toSet());

		Mockito
				.when(busgpsService.getRunningOperators("1354233629999999", "1354233651000000"))
				.thenReturn(operatorsArray);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/task1/1354233629999999/1354233651000000/")
		)
				.andExpect(status().isOk())
				.andExpect(content().string(operatorsArray.toString()));

//		ResponseEntity responseEntity = busgpsController.getRunningOperators("1354233629999999", "1354233651000000");
//		Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
//		Assert.assertEquals(operatorsArray,responseEntity.getBody());
	}

//	@Test
//	public void getRunningOperatorsTest() throws Exception {
//		Set<String> operatorsArray = Stream.of("CD","RD","CF","HN","SL","D1","D2","PO").collect(Collectors.toSet());
//
//		Mockito
//				.when(busgpsService.getRunningOperators("2012-11-30T00:00:29", "2012-11-30T00:00:51"))
//				.thenReturn(operatorsArray);
//
//		ResponseEntity responseEntity = busgpsController.getRunningOperators("2012-11-30T00:00:29", "2012-11-30T00:00:51");
//		Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
//		Assert.assertEquals(operatorsArray,responseEntity.getBody());
//	}

}
