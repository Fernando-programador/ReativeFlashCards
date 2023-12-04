package com.asl.domain.execption;

public class ReactiveExceptionHandler extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ReactiveExceptionHandler(final String message) {
		super(message);
	}

}
