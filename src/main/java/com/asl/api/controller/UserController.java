package com.asl.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.asl.api.request.UserRequest;
import com.asl.api.response.UserResponse;
import com.asl.domain.document.UserDocument;
import com.asl.domain.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Validated
@RequestMapping(value = "/api")
@RestController
@Slf4j
@AllArgsConstructor
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService service;



	@PostMapping(value = "/flux", consumes =MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Mono<UserDocument>> create(@RequestBody final UserDocument request){
		 var newUser = service.create(request)
				 .doFirst(()-> LOGGER.info("*** is save controller user {}!!", request));
				 
				 
		 return new ResponseEntity<>(newUser, HttpStatus.CREATED);
				}
	
	
}
