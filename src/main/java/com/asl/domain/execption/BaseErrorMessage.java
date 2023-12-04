package com.asl.domain.execption;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.validator.internal.util.privilegedactions.GetResource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BaseErrorMessage {

	private final String DEFAULT_RESOURCE = "messages";
	
	private static final BaseErrorMessage GENERIC_EXECPTION = new BaseErrorMessage();
	private static final BaseErrorMessage GENERIC_NOT_FOUND = new BaseErrorMessage();
	private static final BaseErrorMessage GENERIC_METHOD_NOT_ALLOW = new BaseErrorMessage();
			
	
	
	private final String key;
	private String[] params;
	
	public BaseErrorMessage params(final String... params) {
		this.params  =ArrayUtils.clone(params);
		return this;
		
	}
	
	public String getMessage() {
		var message = tryGetMessageFromBundle();
		if (ArrayUtils.isNotEmpty(params)) {
			final var fmt = new MessageFormat(message);
			message = fmt.format(params);
		}
		return message;
	}
	
	private String tryGetMessageFromBundle() {
		return getResource().getString(key);
	}
	
	public ResourceBundle getResource() {
		return ResourceBundle.getBundle(DEFAULT_RESOURCE);
	}
	
}
