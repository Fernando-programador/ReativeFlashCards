package com.asl.core.provider;

import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.stereotype.Component;


@Component("dateTimeProvider")
public class OffsetDateTimeProvider implements DateTimeProvider {

	@Override
	public Optional<TemporalAccessor> getNow() {
		// TODO Auto-generated method stub
		return Optional.of(OffsetDateTime.now());
//		return Optional.of(OffsetDateTime.now(Utf));
	}
	
	

}
