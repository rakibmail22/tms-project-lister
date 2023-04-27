package com.tms.project.api.service.converter;

import com.tms.project.api.UserRequest;
import com.tms.project.api.UserResponse;
import com.tms.project.repository.entity.TmsUserConfig;
import org.springframework.stereotype.Component;

@Component
public class TmsUserConverter {

	public TmsUserConfig convert(UserRequest userRequest) {
		TmsUserConfig userConfig = new TmsUserConfig();
		userConfig.setUsername(userRequest.username());
		userConfig.setPassword(userRequest.password());

		return userConfig;
	}

	public UserResponse convert(TmsUserConfig userConfig) {
		return new UserResponse(
				userConfig.getUuid().toString(),
				userConfig.getUsername(),
				userConfig.getPassword()
		);
	}
}