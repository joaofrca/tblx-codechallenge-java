package com.tblx.api.Controller;

import com.tblx.api.Services.BusgpsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@Tag("Controller")
public class BusgpsControllerEndpointTest {


	@InjectMocks
	BusgpsController busgpsController;

	@Mock
	BusgpsService busgpsService;

	private MockMvc mockMvc;

	@Before
	public void init()
	{
		this.mockMvc = standaloneSetup(busgpsController).build();
	}

	/**
	 * The purpose of this test was to test the controller methods through the endpoint instead of the method itself.
	 * @throws Exception
	 */
	@Test
	@DisplayName("Running Operators Controller Test - still not working")
	public void getRunningOperatorsTest() throws Exception {

		Set<String> operatorsArray = Stream.of("CD","RD","CF","HN","SL","D1","D2","PO").collect(Collectors.toSet());

		Mockito
				.when(busgpsService.getRunningOperators("2012-11-30T00:00:01", "2012-11-30T00:10:00"))
				.thenReturn(operatorsArray);

		MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/task1/2012-11-30T00:00:01/2012-11-30T00:10:00"))
				.andExpect(status().isOk())
				.andReturn();

//		Assert.assertEquals(res.getResponse().getContentAsString(),operatorsArray);
	}
}
