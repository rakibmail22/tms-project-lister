package com.tms.project.utils;

import com.tms.project.api.UserRequest;
import com.tms.project.repository.entity.TmsUserConfig;

import java.util.UUID;

public class TestUtils {

	public static UserRequest createRandomUserRequest() {
		return new UserRequest(String.valueOf(Math.random()), String.valueOf(Math.random()));
	}

	public static TmsUserConfig createRandomTmsUserConfig() {
		TmsUserConfig userConfig = new TmsUserConfig();

		userConfig.setUuid(UUID.randomUUID());
		userConfig.setId((int) (Math.random() * 100));
		userConfig.setUsername(String.valueOf(Math.random()));
		userConfig.setPassword(String.valueOf(Math.random()));
		return userConfig;
	}
}
