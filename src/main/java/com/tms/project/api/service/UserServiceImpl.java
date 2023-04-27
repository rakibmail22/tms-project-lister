package com.tms.project.api.service;

import com.tms.project.api.UserRequest;
import com.tms.project.api.UserResponse;
import com.tms.project.api.service.converter.TmsUserConverter;
import com.tms.project.repository.TmsUserConfigRepository;
import com.tms.project.repository.entity.TmsUserConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final TmsUserConfigRepository userConfigRepository;

	private final TmsUserConverter tmsUserConverter;

	public Page<UserResponse> getList(int offset, int limit) {
		return userConfigRepository.findAll(PageRequest.of(offset, limit))
		                           .map(tmsUserConverter::convert);
	}

	@Override
	public UserResponse create(UserRequest userRequest) {

		TmsUserConfig userConfig = tmsUserConverter.convert(userRequest);

		userConfig = userConfigRepository.save(userConfig);

		return tmsUserConverter.convert(userConfig);
	}

	@Override
	public UserResponse update(String userId, UserRequest userRequest) {
		TmsUserConfig userConfig = userConfigRepository.findByUuid(UUID.fromString(userId))
		                                               .orElseThrow(IllegalArgumentException::new);
		userConfig.setUsername(userRequest.username());
		userConfig.setPassword(userRequest.password());

		userConfig = userConfigRepository.save(userConfig);

		return tmsUserConverter.convert(userConfig);
	}
}
