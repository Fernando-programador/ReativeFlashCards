package com.asl.core.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/*
 * Para colocar a anotação target é preciso colocar @ no tipo se é uma class ou interface
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, 
	ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR, 
	ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MonoIdValidator.class})
public @interface MongoId {
	
	String message() default "{com.asl.reactiveFlashCards.MongoId.message}";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
