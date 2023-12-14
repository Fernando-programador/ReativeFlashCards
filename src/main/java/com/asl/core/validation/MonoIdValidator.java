package com.asl.core.validation;



import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



public class MonoIdValidator implements ConstraintValidator<MongoId, String>{

	private static final Logger LOGGER = LoggerFactory.getLogger(MonoIdValidator.class);

	@Override
	public void initialize(MongoId constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		LOGGER.info("==== checking if {} is a valid mondoDB id", value);		
		return StringUtils.isNotBlank(value) && ObjectId.isValid(value);
	}
	

	
}
