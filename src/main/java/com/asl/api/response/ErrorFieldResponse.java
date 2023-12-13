package com.asl.api.response;

import lombok.Builder;

public record ErrorFieldResponse(String name,
		String message) {
	
	@Builder(toBuilder = true)
	public ErrorFieldResponse{}

}
