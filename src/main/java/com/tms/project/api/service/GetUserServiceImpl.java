package com.tms.project.api.service;

import com.tms.project.api.exception.ResourceNotFoundException;
import com.tms.project.api.model.UserResponse;
import com.tms.project.api.service.converter.TmsUserConverter;
import com.tms.project.repository.TmsUserConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetUserServiceImpl implements GetUserService {

	private static final String RESOURCE_NAME = "TMS User Config";

	private static final String RESOURCE_NOT_FOUND_MSG = "Resource Not Found";

	private final TmsUserConfigRepository userConfigRepository;

	private final TmsUserConverter tmsUserConverter;

	@Override
	public UserResponse get(String id) {
		return userConfigRepository.findByUuid(UUID.fromString(id))
		                           .map(tmsUserConverter::convert)
		                           .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME,
		                                                                            RESOURCE_NOT_FOUND_MSG));
	}

	@Override
	public Page<UserResponse> getList(int offset, int limit) {
		return userConfigRepository.findAll(PageRequest.of(offset, limit))
		                           .map(tmsUserConverter::convert);
	}
}
