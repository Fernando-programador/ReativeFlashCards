package com.asl.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;


public record UserRequest(  @NotBlank
							@Size(min = 1, max = 255 )
							@JsonProperty("name")
							String name,
							@JsonProperty("email")
							@NotBlank 
							@Size(min = 3,max = 255) 
							@Email
							String email
							) {

	@Builder(toBuilder = true)
	public UserRequest{}
}
