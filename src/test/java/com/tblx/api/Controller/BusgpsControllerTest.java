package com.tblx.api.Controller;

import com.tblx.api.Services.BusgpsService;
import com.tblx.api.Services.BusgpsServiceTest;
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

}
