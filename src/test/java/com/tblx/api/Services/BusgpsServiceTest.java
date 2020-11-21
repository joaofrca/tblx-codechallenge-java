package com.tblx.api.Services;

import com.tblx.api.Error.BusgpsDateConversionException;
import com.tblx.api.Error.BusgpsNotFoundException;
import com.tblx.api.Model.Busgps;
import com.tblx.api.Model.VehicleTrace;
import com.tblx.api.Repositories.BusgpsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)

public class BusgpsServiceTest {

	@Mock
	BusgpsRepository busgpsRepository;

	@InjectMocks
	BusgpsService busgpsService;

	Busgps busgpsMock1 = null;
	Busgps busgpsMock2 = null;
	Busgps busgpsMock3 = null;
	Busgps busgpsMock4 = null;
	List<Busgps> busgpsMockArray1 = null;
	List<Busgps> busgpsMockArray2 = null;

	@Before
	public void init()
	{
		busgpsMock1 = new Busgps("1354233630000000", "7", "0", "71003", "2012-11-29", "7060", "D1", "0", "-6.255556", "53.345367", "-974", "7007", "43012", "400", "0");
		busgpsMock2 = new Busgps("1354233630000000", "332", "0", "033B0001", "2012-11-29", "497", "HN", "0", "-6.137816", "53.492287", "54", "332001", "40025", "5069", "1");
		busgpsMock4 = new Busgps("1354233649000000", "83", "0", "830003", "2012-11-29", "6310", "HN", "0", "-6.306583", "53.398151", "0", "83012", "40021", "336", "1");
		busgpsMock3 = new Busgps("1354233630000000", "83", "0", "830003", "2012-11-29", "6310", "HN", "0", "-6.305417", "53.396168", "0", "83012", "40021", "336", "1");
		busgpsMockArray1 = Stream.of(busgpsMock1, busgpsMock2, busgpsMock3, busgpsMock4).collect(Collectors.toList());
		busgpsMockArray2 = Stream.of( busgpsMock3, busgpsMock4).collect(Collectors.toList());
	}

	@Test
	public void getRunningOperatorsTest() throws Exception {

		Set<String> operatorsExpected = Stream.of("HN","D1").collect(Collectors.toSet());
		long microStartTime = this.busgpsService.getMicroTimestamp("2012-11-30T00:00:29");
		long microEndTime = this.busgpsService.getMicroTimestamp("2012-11-30T00:00:51");

		Mockito
				.when(busgpsRepository.findByTimestampBetween(microStartTime, microEndTime))
				.thenReturn(busgpsMockArray1);

		Set<String> operatorsResult = this.busgpsService.getRunningOperators("2012-11-30T00:00:29", "2012-11-30T00:00:51");
		Assert.assertEquals(operatorsExpected, operatorsResult);
	}

	@Test
	public void getVehiclesIDListTest() throws BusgpsNotFoundException, BusgpsDateConversionException {

		Set<Integer> vehiclesIDArrayExpected = Stream.of(43012,40025,40021).collect(Collectors.toSet());
		long microStartTime = this.busgpsService.getMicroTimestamp("2012-11-30T00:00:29");
		long microEndTime = this.busgpsService.getMicroTimestamp("2012-11-30T00:00:51");

		Mockito
				.when(busgpsRepository.findByTimestampBetweenAndOperator(microStartTime, microEndTime, "HN"))
				.thenReturn(busgpsMockArray1);

		Set<Integer> vehiclesIDArrayResult = this.busgpsService.getVehiclesIDList("2012-11-30T00:00:29", "2012-11-30T00:00:51", "HN");
		Assert.assertEquals(vehiclesIDArrayExpected,vehiclesIDArrayResult);
	}

	@Test
	public void getVehiclesAtStopTest() throws BusgpsNotFoundException, BusgpsDateConversionException {

		Set<Integer> vehiclesAtStopArrayExpected = Stream.of(40025, 40021).collect(Collectors.toSet());
		long microStartTime = this.busgpsService.getMicroTimestamp("2012-11-30T00:00:29");
		long microEndTime = this.busgpsService.getMicroTimestamp("2012-11-30T00:00:51");

		Mockito
				.when(busgpsRepository.findByTimestampBetweenAndOperator(microStartTime, microEndTime, "HN"))
				.thenReturn(busgpsMockArray1);

		Set<Integer> vehiclesAtStopArrayResult = this.busgpsService.getVehiclesAtStop("2012-11-30T00:00:29", "2012-11-30T00:00:51", "HN");
		Assert.assertEquals(vehiclesAtStopArrayExpected,vehiclesAtStopArrayResult);
	}

	@Test
	public void getVehicleTraceTest() throws BusgpsNotFoundException, BusgpsDateConversionException {

		List<VehicleTrace> vehicleTraceArrayExpected = Stream.of(new VehicleTrace("2012-11-29", "-6.305417", "53.396168"),
				new VehicleTrace("2012-11-29", "-6.306583", "53.398151"))
				.collect(Collectors.toList());
		long microStartTime = this.busgpsService.getMicroTimestamp("2012-11-30T00:00:29");
		long microEndTime = this.busgpsService.getMicroTimestamp("2012-11-30T00:00:51");

		Mockito
				.when(busgpsRepository.findByTimestampBetweenAndVehicleID(microStartTime, microEndTime, 40021))
				.thenReturn(busgpsMockArray2);

		List<VehicleTrace> vehicleTraceArrayResult = this.busgpsService.getVehicleTrace("2012-11-30T00:00:29", "2012-11-30T00:00:51", 40021	);
		Assert.assertSame(vehicleTraceArrayExpected.toArray(),vehicleTraceArrayResult);
	}

}
