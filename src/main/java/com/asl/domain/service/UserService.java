package com.asl.domain.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asl.domain.document.UserDocument;
import com.asl.domain.repository.UserRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository repository;
	
	
	public Mono<UserDocument> create( final UserDocument document){
		var newUser = repository.save(document)
				.doOnSuccess(saved -> LOGGER.info("*** try to save a floow document {}", document));
							
		return  newUser;
	}
}
