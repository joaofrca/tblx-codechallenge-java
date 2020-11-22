package com.tblx.api.Controller;

import com.tblx.api.Services.OperatorsService;
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
@Tag("OperatorsControllerTest")
public class OperatorsControllerTest {

	@Mock
	OperatorsService operatorsService;

	@InjectMocks
	OperatorsController operatorsController;

	@Test
	@DisplayName("getRunningOperatorsTest")
	public void getRunningOperatorsTest() throws Exception {
		Set<String> operatorsExpected = Stream.of("CD","RD","CF","HN","SL","D1","D2","PO").collect(Collectors.toSet());

		Mockito
				.when(operatorsService.getRunningOperators("2012-11-30T00:00:29", "2012-11-30T00:00:51"))
				.thenReturn(operatorsExpected);

		ResponseEntity responseEntityResult = operatorsController.getRunningOperators("2012-11-30T00:00:29", "2012-11-30T00:00:51");
		Assert.assertEquals(HttpStatus.OK,responseEntityResult.getStatusCode());
		Assert.assertEquals(operatorsExpected,responseEntityResult.getBody());
	}

//	private MockMvc mockMvc;
//
//	@Before
//	public void init()
//	{
//		this.mockMvc = standaloneSetup(operatorsController).build();
//	}
//
//	/**
//	 * The purpose of this test was to test the controller methods through the endpoint instead of the method itself.
//	 * @throws Exception
//	 */
//	@Test
//	@DisplayName("Running Operators Controller Test - still not working")
//	public void getRunningOperatorsTest() throws Exception {
//
//		Set<String> operatorsArray = Stream.of("CD","RD","CF","HN","SL","D1","D2","PO").collect(Collectors.toSet());
//
//		Mockito
//				.when(operatorsService.getRunningOperators("2012-11-30T00:00:01", "2012-11-30T00:10:00"))
//				.thenReturn(operatorsArray);
//
//		MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/task1/2012-11-30T00:00:01/2012-11-30T00:10:00"))
//				.andExpect(status().isOk())
//				.andReturn();
//
//		//Assert.assertEquals(res.getResponse().getContentAsString(),operatorsArray);
//	}

}
