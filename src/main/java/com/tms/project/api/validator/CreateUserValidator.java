package com.tms.project.api.validator;

import com.tms.project.api.exception.ConfigurationAlreadyExistsException;
import com.tms.project.api.service.GetUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserValidator {

	private final GetUserService getUserService;

	public void validate() {
		validateNoOtherConfigAvailable();
	}

	private void validateNoOtherConfigAvailable() {
		if (!getUserService.getList(0, 1).isEmpty()) {
			throw new ConfigurationAlreadyExistsException("configuration",
			                                              "Creating multiple configuration is not supported");
		}
	}
}
