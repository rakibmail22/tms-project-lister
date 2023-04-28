package com.tms.project.api.service;

import com.tms.project.api.exception.ResourceNotFoundException;
import com.tms.project.api.model.request.ConfigRequest;
import com.tms.project.api.model.response.ConfigResponse;
import com.tms.project.api.service.converter.TmsUserConfigConverter;
import com.tms.project.api.validator.CreateConfigValidator;
import com.tms.project.repository.TmsUserConfigRepository;
import com.tms.project.repository.entity.TmsUserConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {

	private static final String RESOURCE_NAME = "TMS User Config";

	private static final String RESOURCE_NOT_FOUND_MSG = "Resource Not Found";

	private final TmsUserConfigRepository userConfigRepository;

	private final TmsUserConfigConverter tmsUserConfigConverter;

	private final CreateConfigValidator createConfigValidator;

	@Override
	public ConfigResponse create(ConfigRequest configRequest) {

		createConfigValidator.validate();

		TmsUserConfig userConfig = tmsUserConfigConverter.convert(configRequest);

		userConfig = userConfigRepository.save(userConfig);

		return tmsUserConfigConverter.convert(userConfig);
	}

	@Override
	public ConfigResponse update(String userId, ConfigRequest configRequest) {
		TmsUserConfig userConfig = userConfigRepository.findByUuid(UUID.fromString(userId))
		                                               .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME,
		                                                                                                RESOURCE_NOT_FOUND_MSG));

		userConfig.setUsername(configRequest.username());
		userConfig.setPassword(configRequest.password());

		userConfig = userConfigRepository.save(userConfig);

		return tmsUserConfigConverter.convert(userConfig);
	}
}
