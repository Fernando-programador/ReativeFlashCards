package com.asl.api.exceptionHandler;

import java.util.List;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;

import com.asl.api.response.ErrorFieldResponse;
import com.asl.api.response.ProblemResponse;
import com.asl.domain.execption.BaseErrorMessage;
import com.asl.domain.execption.NotFoundExecption;
import com.asl.domain.execption.ReactiveExceptionHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
@Order(-2)
@Slf4j
@AllArgsConstructor
public class ApiExceptionHandler implements WebExceptionHandler{
	
	
	private final ObjectMapper mapper;

	private final MessageSource source;
	
	@Override
	public Mono<Void> handle(ServerWebExchange exchange, final Throwable ex) {
	 return	Mono.error(ex)
			 .onErrorResume(MethodNotAllowedException.class, 
					 e -> handlerMethodNotAllowedException(exchange, e))
			 .onErrorResume(NotFoundExecption.class,
					 e -> handlerNotFoundException(exchange, e))
			 .onErrorResume(ResponseStatusException.class,
					 e -> handlerResponseStatusException(exchange, e))
			 .onErrorResume(ConstraintViolationException.class,
					 e -> handlerConstraintViolationException(exchange, e))
			 .onErrorResume(WebExchangeBindException.class,
					 e -> handlerWebExchangeBindException(exchange, e))
			 .onErrorResume(ReactiveExceptionHandler.class, 
					 e -> handlerReactiveException(exchange, e))
			 .onErrorResume(Exception.class, 
					 e -> handlerException(exchange, e))
			 .onErrorResume(JsonProcessingException.class,
					 e -> handlerJsonProcessingException(exchange, e))
			 .then();
	}

	private Mono<Void> handlerMethodNotAllowedException(
			final ServerWebExchange exchange, final MethodNotAllowedException ex){
		return Mono.fromCallable(() -> {
			prepareExchange(exchange, HttpStatus.METHOD_NOT_ALLOWED);
			return BaseErrorMessage.GENERIC_METHOD_NOT_ALLOW.getMessage();
		}).map(message -> buildError(HttpStatus.METHOD_NOT_ALLOWED, message))
				.doFirst(() -> log.error(
						"***handlerMethodNotAllowedException***: Method [{}] is not allowed at [{}]",
						exchange.getRequest().getMethod(), 
						exchange.getRequest().getPath().value(), ex))
				.flatMap(response -> writeResponse(exchange, response));
	}
	
	private Mono<Void> handlerNotFoundException(
			final ServerWebExchange exchange, final NotFoundExecption ex){
		return Mono.fromCallable(() -> {
			prepareExchange(exchange, HttpStatus.NOT_FOUND);
			return ex.getMessage();
		}).map(message -> buildError(HttpStatus.NOT_FOUND, message))
				.doFirst(() -> log.error(
						"***handlerNotFoundException***", ex))
		.flatMap(response -> writeResponse(exchange, response));
	}
	
	private Mono<Void> handlerResponseStatusException(
			final ServerWebExchange exchange, final ResponseStatusException ex){
		return Mono.fromCallable(() -> {
			prepareExchange(exchange, HttpStatus.NOT_FOUND);
			return BaseErrorMessage.GENERIC_NOT_FOUND.getMessage();
		}).map(message -> buildError(HttpStatus.NOT_FOUND, message))
				.doFirst(() -> log.error(
						"***handlerResponseStatusException***:", ex))
				.flatMap(response -> writeResponse(exchange, response));
	}
	
	private Mono<Void> handlerConstraintViolationException(
			final ServerWebExchange exchange, final ConstraintViolationException ex){
		return Mono.fromCallable(() -> {
			prepareExchange(exchange, HttpStatus.BAD_REQUEST);
			return BaseErrorMessage.GENERIC_BAD_REQUEST.getMessage();
		}).map(message -> buildError(HttpStatus.BAD_REQUEST, message))
				.flatMap(response -> buildParamErrorMessage(response, ex))
				.doFirst(() -> log.error(
						"***handlerConstraintViolationException***:", ex))
				.flatMap(response -> writeResponse(exchange, response));
	}
	
	private Mono<Void> handlerWebExchangeBindException(
			final ServerWebExchange exchange, final WebExchangeBindException ex){
		return Mono.fromCallable(() -> {
			prepareExchange(exchange, HttpStatus.BAD_REQUEST);
			return BaseErrorMessage.GENERIC_BAD_REQUEST.getMessage();
		}).map(message -> buildError(HttpStatus.BAD_REQUEST, message))
				.flatMap(response -> buildParamErrorMessage(response, ex))
				.doFirst(() -> log.error(
						"***handlerWebExchangeBindException***:", ex))
				.flatMap(response -> writeResponse(exchange, response));
	}
	
	private Mono<Void> handlerReactiveException(
			final ServerWebExchange exchange, final ReactiveExceptionHandler ex){
		return Mono.fromCallable(() -> {
			prepareExchange(exchange, HttpStatus.INTERNAL_SERVER_ERROR);
			return BaseErrorMessage.GENERIC_EXECPTION.getMessage();
		}).map(message -> buildError(HttpStatus.INTERNAL_SERVER_ERROR, message))
				.doFirst(() -> log.error(
						"***handlerReactiveException***:", ex))
				.flatMap(response -> writeResponse(exchange, response));
	}
	
	private Mono<Void> handlerException(
			final ServerWebExchange exchange, final Exception ex){
		return Mono.fromCallable(() -> {
			prepareExchange(exchange, HttpStatus.INTERNAL_SERVER_ERROR);
			return BaseErrorMessage.GENERIC_EXECPTION.getMessage();
		}).map(message -> buildError(HttpStatus.INTERNAL_SERVER_ERROR, message))
				.doFirst(() -> log.error(
						"***handlerException***: Exception [{}] error [{}]",
						exchange.getRequest().getMethod(), 
						exchange.getRequest().getPath().value(), ex))
				.flatMap(response -> writeResponse(exchange, response));
	}
	

	
	
	private Mono<Void> handlerJsonProcessingException(
			final ServerWebExchange exchange, final JsonProcessingException ex){
		return Mono.fromCallable(() -> {
			prepareExchange(exchange, HttpStatus.METHOD_NOT_ALLOWED);
			return BaseErrorMessage.GENERIC_METHOD_NOT_ALLOW.getMessage();
		}).map(message -> buildError(HttpStatus.METHOD_NOT_ALLOWED, message))
				.doFirst(() -> log.error(
						"***handlerJsonProcessingException***: Failed to map exception for the request [{}]",
						exchange.getRequest().getPath().value(), ex))
				.flatMap(response -> writeResponse(exchange, response));
	}
	
	private Mono<ProblemResponse> buildParamErrorMessage(
			final ProblemResponse response, final WebExchangeBindException ex){
		return Flux.fromIterable(ex.getAllErrors())
				.map(objError -> ErrorFieldResponse.builder()
								.name(objError.getObjectName())
								.message(source.getMessage(
										objError, LocaleContextHolder.getLocale()))
								.build())
				.collectList()
				.map(problems -> response.toBuilder().fields(problems).build());
	}
	
	private Mono<ProblemResponse> buildParamErrorMessage(
			final ProblemResponse response, final ConstraintViolationException ex){
		return Flux.fromIterable(ex.getConstraintViolations())
				.map(constraint -> ErrorFieldResponse.builder()
						.name(((PathImpl) constraint.getPropertyPath()).getLeafNode().toString())
						.message(constraint.getMessage()).build())
				.collectList()
				.map(problems -> response.toBuilder().fields(problems).build());
	}
	
	private Mono<Void> writeResponse(
			final ServerWebExchange exchange, ProblemResponse response){
		return exchange.getResponse()
				.writeWith(Mono.fromCallable(() -> new DefaultDataBufferFactory()
						.wrap(mapper.writeValueAsBytes(response))));
	}
	
	
	private void prepareExchange(
			final ServerWebExchange exchange, final HttpStatus status) {
		exchange.getResponse().setStatusCode(status);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
	}
	
	
	private ProblemResponse buildError(
			final HttpStatus status, final String description) {
		return  ProblemResponse.builder()
				.status(status.value())
				.errorDescription(description)
				.build();
				
	}
}
