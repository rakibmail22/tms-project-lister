package com.tms.project.tmsclient.config;

import com.tms.project.api.service.TmsAuthService;
import com.tms.project.tmsclient.model.TmsAuthToken;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TmsRequestInterceptor implements RequestInterceptor {

	private static final String AUTHORIZATION_HEADER = "Authorization";

	private static final String API_KEY = "ApiToken";

	private final TmsAuthService tmsAuthService;

	@Override
	public void apply(RequestTemplate template) {
		TmsAuthToken token = tmsAuthService.getToken();
		template.header(AUTHORIZATION_HEADER, "%s %s ".formatted(API_KEY, token.token()));
	}
}