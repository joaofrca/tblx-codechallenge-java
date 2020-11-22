package com.tblx.api.Services;

import com.tblx.api.Error.BusgpsDateConversionException;
import com.tblx.api.Error.BusgpsException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

@RunWith(MockitoJUnitRunner.class)
@Tag("UtilsServiceTest")
public class UtilsServiceTest {

	@InjectMocks
	UtilsService utilsService;

	String iso8601Mock1;
	String iso8601Mock2;

	@Before
	public void init()
	{
		iso8601Mock1 = "2012-11-30T00:00:29";
		iso8601Mock2 = "2012-11-30T00:ERROR00:29";
	}

	@Test
	@DisplayName("getMicroTimestamp")
	public void getMicroTimestampTest() throws BusgpsDateConversionException {
		long microTimeStampExpected = 1354233629000000L;
		long microTimeStampResult = this.utilsService.getMicroTimestamp(iso8601Mock1);
		Assert.assertEquals(microTimeStampExpected, microTimeStampResult);
	}

	@Test
	@DisplayName("getMicroTimestamp - exception")
	public void getMicroTimestampExceptionTest() {
		try {
			this.utilsService.getMicroTimestamp(iso8601Mock2);
		} catch (BusgpsException e) {
			Assert.assertEquals("Date conversion error. Bad Request." ,e.getErrorMessage());
			Assert.assertEquals(HttpStatus.BAD_REQUEST ,e.getStatusCode());
		}
	}

}

