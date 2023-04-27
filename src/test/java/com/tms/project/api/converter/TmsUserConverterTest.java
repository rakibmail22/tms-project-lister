package com.tms.project.api.converter;

import com.tms.project.api.UserRequest;
import com.tms.project.api.UserResponse;
import com.tms.project.api.service.converter.TmsUserConverter;
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
public class TmsUserConverterTest {

	private final TmsUserConverter tmsUserConverter;

	@Autowired
	public TmsUserConverterTest(TmsUserConverter tmsUserConverter) {
		this.tmsUserConverter = tmsUserConverter;
	}

	@Test
	public void convert_GivenUserRequestAsInput_ConvertsToTmsUserConfig() {
		UserRequest request = createRandomUserRequest();
		TmsUserConfig tmsUserConfig = tmsUserConverter.convert(request);

		Assertions.assertEquals(request.username(), tmsUserConfig.getUsername());
		Assertions.assertEquals(request.password(), tmsUserConfig.getPassword());
	}

	@Test
	public void convert_GivenUserRequestAsInput_ReturnsTmsUserConfigWithoutId() {
		UserRequest request = createRandomUserRequest();
		TmsUserConfig tmsUserConfig = tmsUserConverter.convert(request);

		Assertions.assertNull(tmsUserConfig.getId());
	}

	@Test
	public void convert_GivenUserRequestAsInput_ReturnsTmsUserConfigWithUuid() {
		UserRequest request = createRandomUserRequest();
		TmsUserConfig tmsUserConfig = tmsUserConverter.convert(request);

		Assertions.assertNotNull(tmsUserConfig.getUuid());
	}

	@Test
	public void convert_GivenTmsUserConfigAsInput_ConvertsToUserResponse() {
		TmsUserConfig tmsUserConfig = createRandomTmsUserConfig();

		UserResponse userResponse = tmsUserConverter.convert(tmsUserConfig);

		Assertions.assertEquals(tmsUserConfig.getUuid().toString(), userResponse.id());
		Assertions.assertEquals(tmsUserConfig.getUsername(), userResponse.username());
		Assertions.assertEquals(tmsUserConfig.getPassword(), userResponse.password());
	}
}
