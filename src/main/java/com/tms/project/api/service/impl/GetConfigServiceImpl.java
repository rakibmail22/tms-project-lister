package com.tms.project.api.service.impl;

import com.tms.project.api.exception.ResourceNotFoundException;
import com.tms.project.api.model.response.ConfigResponse;
import com.tms.project.api.service.GetConfigService;
import com.tms.project.api.service.converter.TmsUserConfigConverter;
import com.tms.project.repository.TmsUserConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetConfigServiceImpl implements GetConfigService {

	private static final String RESOURCE_NAME = "TMS User Config";

	private static final String RESOURCE_NOT_FOUND_MSG = "Resource Not Found";

	private final TmsUserConfigRepository userConfigRepository;

	private final TmsUserConfigConverter tmsUserConfigConverter;

	@Override
	public ConfigResponse get(String id) {
		return userConfigRepository.findByUuid(UUID.fromString(id))
		                           .map(tmsUserConfigConverter::convert)
		                           .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME,
		                                                                            RESOURCE_NOT_FOUND_MSG));
	}

	@Override
	public Page<ConfigResponse> getList(int offset, int limit) {
		return userConfigRepository.findAll(PageRequest.of(offset, limit))
		                           .map(tmsUserConfigConverter::convert);
	}

	@Override
	public ConfigResponse findByUsername(String username) {
		return userConfigRepository.findByUsername(username)
		                           .map(tmsUserConfigConverter::convert)
		                           .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME,
		                                                                            RESOURCE_NOT_FOUND_MSG));
	}

	@Override
	public ConfigResponse getConfig() {
		return userConfigRepository.findAll().stream()
		                           .findFirst()
		                           .map(tmsUserConfigConverter::convert)
		                           .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME,
		                                                                            RESOURCE_NOT_FOUND_MSG));
	}
}
