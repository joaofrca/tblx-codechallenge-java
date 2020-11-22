package com.tblx.api.Services;

import com.tblx.api.Error.BusgpsDateConversionException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class UtilsService {

	public long getMicroTimestamp (String iso8601str) throws BusgpsDateConversionException {
		try {
			long epochMicros = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(iso8601str).getTime()*1000L ;
			return epochMicros;
		} catch (Exception e) {
			throw new BusgpsDateConversionException();
		}
	}

}
