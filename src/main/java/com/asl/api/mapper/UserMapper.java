package com.asl.api.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asl.api.request.UserRequest;
import com.asl.api.response.UserResponse;
import com.asl.domain.document.UserDocument;

import lombok.experimental.FieldNameConstants;

@Service
@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(target = "id", ignore=true)
	@Mapping(target = "createdAt", ignore=true)
	@Mapping(target = "updateAt", ignore=true)
	UserDocument toDocument(final UserRequest request);
	 
	 UserResponse toResponse(final UserDocument document);
	
}
