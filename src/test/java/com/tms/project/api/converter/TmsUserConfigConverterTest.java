package com.tms.project.api.converter;

import com.tms.project.api.model.request.ConfigRequest;
import com.tms.project.api.model.response.ConfigResponse;
import com.tms.project.api.service.converter.TmsUserConfigConverter;
import com.tms.project.repository.entity.TmsUserConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.tms.project.utils.TestUtils.createRandomTmsUserConfig;
import static com.tms.project.utils.TestUtils.createRandomUserRequest;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TmsUserConfigConverterTest {

	private final TmsUserConfigConverter tmsUserConfigConverter;

	@Autowired
	public TmsUserConfigConverterTest(TmsUserConfigConverter tmsUserConfigConverter) {
		this.tmsUserConfigConverter = tmsUserConfigConverter;
	}

	@Test
	public void convert_GivenUserRequestAsInput_ConvertsToTmsUserConfig() {
		ConfigRequest request = createRandomUserRequest();
		TmsUserConfig tmsUserConfig = tmsUserConfigConverter.convert(request);

		Assertions.assertEquals(request.username(), tmsUserConfig.getUsername());
		Assertions.assertEquals(request.password(), tmsUserConfig.getPassword());
	}

	@Test
	public void convert_GivenUserRequestAsInput_ReturnsTmsUserConfigWithoutId() {
		ConfigRequest request = createRandomUserRequest();
		TmsUserConfig tmsUserConfig = tmsUserConfigConverter.convert(request);

		Assertions.assertNull(tmsUserConfig.getId());
	}

	@Test
	public void convert_GivenUserRequestAsInput_ReturnsTmsUserConfigWithUuid() {
		ConfigRequest request = createRandomUserRequest();
		TmsUserConfig tmsUserConfig = tmsUserConfigConverter.convert(request);

		Assertions.assertNotNull(tmsUserConfig.getUuid());
	}

	@Test
	public void convert_GivenTmsUserConfigAsInput_ConvertsToUserResponse() {
		TmsUserConfig tmsUserConfig = createRandomTmsUserConfig();

		ConfigResponse configResponse = tmsUserConfigConverter.convert(tmsUserConfig);

		Assertions.assertEquals(tmsUserConfig.getUuid().toString(), configResponse.id());
		Assertions.assertEquals(tmsUserConfig.getUsername(), configResponse.username());
		Assertions.assertEquals(tmsUserConfig.getPassword(), configResponse.password());
	}
}
