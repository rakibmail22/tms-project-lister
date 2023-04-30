package com.tms.project.api.service;

import com.tms.project.api.model.request.ConfigRequest;
import com.tms.project.api.model.response.ConfigResponse;
import com.tms.project.api.service.converter.TmsUserConfigConverter;
import com.tms.project.api.service.impl.ConfigServiceImpl;
import com.tms.project.api.validator.CreateConfigValidator;
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

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ConfigServiceTest {

	@Mock
	private TmsUserConfigRepository tmsUserConfigRepository;

	@Mock
	private TmsUserConfigConverter tmsUserConfigConverter;

	@Mock
	private CreateConfigValidator createConfigValidator;

	@InjectMocks
	private ConfigServiceImpl configService;

	@Test
	public void save_GivenAValidUserRequest_convertsTheRequestCallsRepoSaveAndReturnsResponse() {
		ConfigRequest mockConfigRequest = TestUtils.createRandomUserRequest();
		TmsUserConfig mockTmsUserConfig = getMockConfigFromRequest(mockConfigRequest);
		ConfigResponse mockConfigResponse = getMockUserResponse(mockTmsUserConfig);

		Mockito.when(tmsUserConfigConverter.convert(ArgumentMatchers.any(ConfigRequest.class)))
		       .thenReturn(mockTmsUserConfig);

		Mockito.when(tmsUserConfigConverter.convert(ArgumentMatchers.any(TmsUserConfig.class)))
		       .thenReturn(mockConfigResponse);

		Mockito.when(tmsUserConfigRepository.save(ArgumentMatchers.any(TmsUserConfig.class)))
		       .thenReturn(mockTmsUserConfig);

		ConfigResponse configResponse = configService.create(mockConfigRequest);

		Assertions.assertEquals(mockConfigRequest.username(), configResponse.username());
		Assertions.assertEquals(mockConfigRequest.password(), configResponse.password());

		InOrder inOrder = Mockito.inOrder(tmsUserConfigConverter, tmsUserConfigRepository);

		inOrder.verify(tmsUserConfigConverter).convert(ArgumentMatchers.any(ConfigRequest.class));
		inOrder.verify(tmsUserConfigRepository).save(ArgumentMatchers.any(TmsUserConfig.class));
		inOrder.verify(tmsUserConfigConverter).convert(ArgumentMatchers.any(TmsUserConfig.class));
	}

	@Test
	public void save_GivenInvalidUserRequest_validatorIsCalled() {
		ConfigRequest mockConfigRequest = TestUtils.createRandomUserRequest();
		TmsUserConfig mockTmsUserConfig = getMockConfigFromRequest(mockConfigRequest);
		ConfigResponse mockConfigResponse = getMockUserResponse(mockTmsUserConfig);

		Mockito.when(tmsUserConfigConverter.convert(ArgumentMatchers.any(ConfigRequest.class)))
		       .thenReturn(mockTmsUserConfig);

		Mockito.when(tmsUserConfigConverter.convert(ArgumentMatchers.any(TmsUserConfig.class)))
		       .thenReturn(mockConfigResponse);

		Mockito.when(tmsUserConfigRepository.save(ArgumentMatchers.any(TmsUserConfig.class)))
		       .thenReturn(mockTmsUserConfig);

		ConfigResponse configResponse = configService.create(mockConfigRequest);

		Mockito.verify(createConfigValidator).validate();
	}

	@Test
	public void update_GivenAValidUserRequest_convertsTheRequestCallsRepoSaveAndReturnsResponse() {
		String userId = UUID.randomUUID().toString();
		ConfigRequest mockConfigRequest = TestUtils.createRandomUserRequest();
		TmsUserConfig mockTmsUserConfig = getMockConfigFromRequest(mockConfigRequest);
		ConfigResponse mockConfigResponse = getMockUserResponse(mockTmsUserConfig);

		Mockito.when(tmsUserConfigConverter.convert(ArgumentMatchers.any(TmsUserConfig.class)))
		       .thenReturn(mockConfigResponse);

		Mockito.when(tmsUserConfigRepository.save(ArgumentMatchers.any(TmsUserConfig.class)))
		       .thenReturn(mockTmsUserConfig);

		Mockito.when(tmsUserConfigRepository.findByUuid(ArgumentMatchers.any(UUID.class)))
		       .thenReturn(Optional.of(mockTmsUserConfig));

		ConfigResponse configResponse = configService.update(userId, mockConfigRequest);

		Assertions.assertEquals(mockConfigRequest.username(), configResponse.username());
		Assertions.assertEquals(mockConfigRequest.password(), configResponse.password());

		InOrder inOrder = Mockito.inOrder(tmsUserConfigConverter, tmsUserConfigRepository);

		inOrder.verify(tmsUserConfigRepository).findByUuid(ArgumentMatchers.any(UUID.class));
		inOrder.verify(tmsUserConfigRepository).save(ArgumentMatchers.any(TmsUserConfig.class));
		inOrder.verify(tmsUserConfigConverter).convert(ArgumentMatchers.any(TmsUserConfig.class));
	}

	private TmsUserConfig getMockConfigFromRequest(ConfigRequest request) {
		TmsUserConfig tmsUserConfig = new TmsUserConfig();
		tmsUserConfig.setId(123);
		tmsUserConfig.setUuid(UUID.randomUUID());
		tmsUserConfig.setUsername(request.username());
		tmsUserConfig.setPassword(request.password());

		return tmsUserConfig;
	}

	private ConfigResponse getMockUserResponse(TmsUserConfig tmsUserConfig) {
		return new ConfigResponse(tmsUserConfig.getUuid().toString(),
		                          tmsUserConfig.getUsername(),
		                          tmsUserConfig.getPassword());
	}
}
