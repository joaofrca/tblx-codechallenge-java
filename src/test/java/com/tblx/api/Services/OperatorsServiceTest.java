package com.tblx.api.Services;

import com.tblx.api.Error.BusgpsDateConversionException;
import com.tblx.api.Error.BusgpsNotFoundException;
import com.tblx.api.Model.Busgps;
import com.tblx.api.Repositories.BusgpsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
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
@Tag("OperatorsServiceTest")
public class OperatorsServiceTest {

	@Mock
	BusgpsRepository busgpsRepository;

	@Mock
	UtilsService utilsService;

	@InjectMocks
	OperatorsService operatorsService;

	Busgps busgpsMock1;
	Busgps busgpsMock2;
	Busgps busgpsMock3;
	Busgps busgpsMock4;
	List<Busgps> busgpsMockArray1;

	@Before
	public void init() throws BusgpsDateConversionException {
		this.busgpsMock1 = new Busgps("1354233630000000", "7", "0", "71003", "2012-11-29", "7060", "D1", "0", "-6.255556", "53.345367", "-974", "7007", "43012", "400", "0");
		this.busgpsMock2 = new Busgps("1354233630000000", "332", "0", "033B0001", "2012-11-29", "497", "HN", "0", "-6.137816", "53.492287", "54", "332001", "40025", "5069", "1");
		this.busgpsMock4 = new Busgps("1354233649000000", "83", "0", "830003", "2012-11-29", "6310", "HN", "0", "-6.306583", "53.398151", "0", "83012", "40021", "336", "1");
		this.busgpsMock3 = new Busgps("1354233630000000", "83", "0", "830003", "2012-11-29", "6310", "HN", "0", "-6.305417", "53.396168", "0", "83012", "40021", "336", "1");
		this.busgpsMockArray1 = Stream.of(busgpsMock1, busgpsMock2, busgpsMock3, busgpsMock4).collect(Collectors.toList());

		long microStartTime = this.utilsService.getMicroTimestamp("2012-11-30T00:00:29");
		long microEndTime = this.utilsService.getMicroTimestamp("2012-11-30T00:00:51");

		Mockito
				.when(utilsService.getMicroTimestamp("2012-11-30T00:00:29"))
				.thenReturn(microStartTime);
		Mockito
				.when(utilsService.getMicroTimestamp("2012-11-30T00:00:51"))
				.thenReturn(microEndTime);
		Mockito
				.when(busgpsRepository.findByTimestampBetween(microStartTime, microEndTime))
				.thenReturn(busgpsMockArray1);
	}

	@Test
	@Tag("getRunningOperatorsTest")
	public void getRunningOperatorsTest() throws BusgpsDateConversionException, BusgpsNotFoundException {

		Set<String> operatorsExpected = Stream.of("HN","D1").collect(Collectors.toSet());

		Set<String> operatorsResult = this.operatorsService.getRunningOperators("2012-11-30T00:00:29", "2012-11-30T00:00:51");
		Assert.assertEquals(operatorsExpected, operatorsResult);
	}

}
