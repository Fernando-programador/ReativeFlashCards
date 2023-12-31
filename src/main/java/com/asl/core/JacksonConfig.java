package com.asl.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class JacksonConfig {

	@Bean
	ObjectMapper mapper() {
		return new ObjectMapper()
				.registerModule(new JavaTimeModule())
				.setSerializationInclusion(Include.NON_NULL)
				.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
	
}

	
	@Bean
	Jackson2JsonEncoder encoder(final ObjectMapper mapper) {
		return new Jackson2JsonEncoder(mapper);
	}
	
	@Bean
	Jackson2JsonDecoder decoder(final ObjectMapper mapper) {
		return new Jackson2JsonDecoder(mapper);
	}
	
}
	