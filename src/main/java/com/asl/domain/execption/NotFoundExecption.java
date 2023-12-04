package com.asl.domain.execption;

public class NotFoundExecption extends ReactiveExceptionHandler {
	private static final long serialVersionUID = 1L;

	public NotFoundExecption(final String message) {
		super(message);
	}
}
