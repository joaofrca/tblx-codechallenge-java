package com.tblx.api.Services;

import com.tblx.api.Error.BusgpsDateConversionException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class UtilsService {

	/**
	 * Converts a ISO 8601 date string into microseconds.
	 * @param iso8601str date in a format such as "2012-11-30T00:00:29"
	 * @return microseconds since 1970 01 01 00:00:00 GMT.
	 * @throws BusgpsDateConversionException
	 */
	public long getMicroTimestamp (String iso8601str) throws BusgpsDateConversionException {
		try {
			long epochMicros = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(iso8601str).getTime()*1000L ;
			return epochMicros;
		} catch (Exception e) {
			throw new BusgpsDateConversionException();
		}
	}

}
