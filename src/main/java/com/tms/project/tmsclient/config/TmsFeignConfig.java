package com.tms.project.tmsclient.config;

import com.tms.project.api.service.TmsAuthService;
import org.springframework.context.annotation.Bean;

public class TmsFeignConfig {

	@Bean
	public TmsRequestInterceptor tmsRequestInterceptor(TmsAuthService tmsAuthService) {
		return new TmsRequestInterceptor(tmsAuthService);
	}
}
