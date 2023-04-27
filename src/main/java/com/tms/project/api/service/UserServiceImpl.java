package com.tms.project.api.service;

import com.tms.project.api.exception.ResourceNotFoundException;
import com.tms.project.api.model.UserRequest;
import com.tms.project.api.model.UserResponse;
import com.tms.project.api.service.converter.TmsUserConverter;
import com.tms.project.api.validator.CreateUserValidator;
import com.tms.project.repository.TmsUserConfigRepository;
import com.tms.project.repository.entity.TmsUserConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private static final String RESOURCE_NAME = "TMS User Config";

	private static final String RESOURCE_NOT_FOUND_MSG = "Resource Not Found";

	private final TmsUserConfigRepository userConfigRepository;

	private final TmsUserConverter tmsUserConverter;

	private final CreateUserValidator createUserValidator;

	@Override
	public UserResponse create(UserRequest userRequest) {

		createUserValidator.validate();

		TmsUserConfig userConfig = tmsUserConverter.convert(userRequest);

		userConfig = userConfigRepository.save(userConfig);

		return tmsUserConverter.convert(userConfig);
	}

	@Override
	public UserResponse update(String userId, UserRequest userRequest) {
		TmsUserConfig userConfig = userConfigRepository.findByUuid(UUID.fromString(userId))
		                                               .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME,
		                                                                                                RESOURCE_NOT_FOUND_MSG));

		userConfig.setUsername(userRequest.username());
		userConfig.setPassword(userRequest.password());

		userConfig = userConfigRepository.save(userConfig);

		return tmsUserConverter.convert(userConfig);
	}
}
