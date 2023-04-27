package com.tms.project.api.exception;

import lombok.Getter;

@Getter
public class ConfigurationAlreadyExistsException extends RuntimeException {

	private final String resourceName;

	private final String msg;

	public ConfigurationAlreadyExistsException(String resourceName, String msg) {
		super();
		this.resourceName = resourceName;
		this.msg = msg;
	}
}
