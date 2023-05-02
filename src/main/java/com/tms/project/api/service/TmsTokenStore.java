package com.tms.project.api.service;

import com.tms.project.api.model.response.ConfigResponse;
import com.tms.project.tmsclient.TmsAuthFeignClient;
import com.tms.project.tmsclient.model.TmsAuthRequest;
import com.tms.project.tmsclient.model.TmsAuthToken;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TmsTokenStore {

	private final GetConfigService getConfigService;

	private final TmsAuthFeignClient tmsAuthFeignClient;

	private final ConcurrentHashMap<String, TmsAuthToken> usernameTokenCache;

	public TmsTokenStore(TmsAuthFeignClient tmsAuthFeignClient, GetConfigService getConfigService) {
		this.usernameTokenCache = new ConcurrentHashMap<>();
		this.tmsAuthFeignClient = tmsAuthFeignClient;
		this.getConfigService = getConfigService;
	}

	public TmsAuthToken getToken() {
		ConfigResponse config = getConfigService.getConfig();

		TmsAuthToken cachedAuthToken = Optional.ofNullable(usernameTokenCache.get(config.username()))
		                                       .filter(this::isExpired)
		                                       .orElse(null);

		if (Objects.isNull(cachedAuthToken)) {
			TmsAuthToken authToken = tmsAuthFeignClient.login(new TmsAuthRequest(config.username(), config.password()));
			usernameTokenCache.put(config.username(), authToken);
		}

		return usernameTokenCache.get(config.username());
	}

	private boolean isExpired(TmsAuthToken authToken) {
		var currentTime = ZonedDateTime.now(authToken.expires().getZone());
		return authToken.expires().isBefore(currentTime);
	}
}
