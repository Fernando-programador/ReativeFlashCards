package com.asl.api.exceptionHandler;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;

import com.asl.domain.execption.ReactiveExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Order(value = -2)
@Slf4j
@AllArgsConstructor
public class ApiExceptionHandler implements WebExceptionHandler{

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
		return Mono.error(ex)
				.onErrorResume(Exception.class, e -> handlerException(exchange, e));
	}

	private Mono<Void> handlerException(
			final ServerWebExchange exchange, final Exception ex){
		return Mono.fromCallable(() -> {
				return GENERIC_EXECPTION;
		})
	}
	private void prepareExchange() {
		
	}
	
}
