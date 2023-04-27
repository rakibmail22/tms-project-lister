package com.tms.project.api.service;

import com.tms.project.api.model.UserRequest;
import com.tms.project.api.model.UserResponse;
import com.tms.project.api.service.converter.TmsUserConverter;
import com.tms.project.repository.TmsUserConfigRepository;
import com.tms.project.repository.entity.TmsUserConfig;
import com.tms.project.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private TmsUserConfigRepository tmsUserConfigRepository;

	@Mock
	private TmsUserConverter tmsUserConverter;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	public void save_GivenAValidUserRequest_convertsTheRequestCallsRepoSaveAndReturnsResponse() {
		UserRequest mockUserRequest = TestUtils.createRandomUserRequest();
		TmsUserConfig mockTmsUserConfig = getMockConfigFromRequest(mockUserRequest);
		UserResponse mockUserResponse = getMockUserResponse(mockTmsUserConfig);

		Mockito.when(tmsUserConverter.convert(ArgumentMatchers.any(UserRequest.class)))
		       .thenReturn(mockTmsUserConfig);

		Mockito.when(tmsUserConverter.convert(ArgumentMatchers.any(TmsUserConfig.class)))
		       .thenReturn(mockUserResponse);

		Mockito.when(tmsUserConfigRepository.save(ArgumentMatchers.any(TmsUserConfig.class)))
		       .thenReturn(mockTmsUserConfig);

		UserResponse userResponse = userService.create(mockUserRequest);

		Assertions.assertEquals(mockUserRequest.username(), userResponse.username());
		Assertions.assertEquals(mockUserRequest.password(), userResponse.password());

		InOrder inOrder = Mockito.inOrder(tmsUserConverter, tmsUserConfigRepository);

		inOrder.verify(tmsUserConverter).convert(ArgumentMatchers.any(UserRequest.class));
		inOrder.verify(tmsUserConfigRepository).save(ArgumentMatchers.any(TmsUserConfig.class));
		inOrder.verify(tmsUserConverter).convert(ArgumentMatchers.any(TmsUserConfig.class));
	}

	private TmsUserConfig getMockConfigFromRequest(UserRequest request) {
		TmsUserConfig tmsUserConfig = new TmsUserConfig();
		tmsUserConfig.setId(123);
		tmsUserConfig.setUuid(UUID.randomUUID());
		tmsUserConfig.setUsername(request.username());
		tmsUserConfig.setPassword(request.password());

		return tmsUserConfig;
	}

	private UserResponse getMockUserResponse(TmsUserConfig tmsUserConfig) {
		return new UserResponse(tmsUserConfig.getUuid().toString(),
		                        tmsUserConfig.getUsername(),
		                        tmsUserConfig.getPassword());
	}
}
