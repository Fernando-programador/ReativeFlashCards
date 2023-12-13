package com.asl.api.response;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;


@JsonInclude(value = Include.NON_NULL)
public record ProblemResponse(Integer status,
		OffsetDateTime timestamp,
		String errorDescription,
		List<ErrorFieldResponse> fields) {

	@Builder(toBuilder = true)
	public ProblemResponse{}

}	

	

