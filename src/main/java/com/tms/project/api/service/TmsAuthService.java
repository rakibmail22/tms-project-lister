package com.tms.project.api.service;

import com.tms.project.api.model.response.ConfigResponse;
import com.tms.project.webclient.TmsAuthFeignClient;
import com.tms.project.webclient.model.TmsAuthRequest;
import com.tms.project.webclient.model.TmsAuthToken;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TmsAuthService {

	private final GetConfigService getConfigService;

	private final TmsAuthFeignClient tmsAuthFeignClient;

	private final ConcurrentHashMap<String, TmsAuthToken> usernameTokenCache;

	public TmsAuthService(TmsAuthFeignClient tmsAuthFeignClient, GetConfigService getConfigService) {
		this.usernameTokenCache = new ConcurrentHashMap<>();
		this.tmsAuthFeignClient = tmsAuthFeignClient;
		this.getConfigService = getConfigService;
	}

	public TmsAuthToken getToken(String username, String password) {
		ConfigResponse config = getConfigService.getConfig();
		username = config.username();
		password = config.password();

		TmsAuthToken cachedAuthToken = Optional.ofNullable(usernameTokenCache.get(username))
		                                       .filter(this::isExpired)
		                                       .orElse(null);

		if (Objects.isNull(cachedAuthToken)) {
			TmsAuthToken authToken = tmsAuthFeignClient.login(new TmsAuthRequest(username, password));
			usernameTokenCache.put(username, authToken);
		}

		return usernameTokenCache.get(username);
	}

	private boolean isExpired(TmsAuthToken authToken) {
		var currentTime = ZonedDateTime.now(authToken.expires().getZone());
		return authToken.expires().isBefore(currentTime);
	}
}
