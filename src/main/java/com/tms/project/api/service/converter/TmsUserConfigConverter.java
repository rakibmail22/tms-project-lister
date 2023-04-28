package com.tms.project.api.service.converter;

import com.tms.project.api.model.request.ConfigRequest;
import com.tms.project.api.model.response.ConfigResponse;
import com.tms.project.repository.entity.TmsUserConfig;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TmsUserConfigConverter {

	public TmsUserConfig convert(ConfigRequest configRequest) {
		TmsUserConfig userConfig = new TmsUserConfig();
		userConfig.setUsername(configRequest.username());
		userConfig.setPassword(configRequest.password());
		userConfig.setUuid(UUID.randomUUID());

		return userConfig;
	}

	public ConfigResponse convert(TmsUserConfig userConfig) {
		return new ConfigResponse(
				userConfig.getUuid().toString(),
				userConfig.getUsername(),
				userConfig.getPassword()
		);
	}
}
