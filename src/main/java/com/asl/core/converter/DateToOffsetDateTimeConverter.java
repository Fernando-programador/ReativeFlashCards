package com.asl.core.converter;

import java.time.OffsetDateTime;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateToOffsetDateTimeConverter implements Converter<OffsetDateTime, Date>{

	/*
	 * esta classe faz a conversão das datas do java para mongodb 
	 */
	@Override
	public Date convert(OffsetDateTime source) {
		return Date.from(source.toInstant());
	}
	
}

