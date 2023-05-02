package com.tms.project.tmsclient.config;

import com.tms.project.api.service.TmsTokenStore;
import org.springframework.context.annotation.Bean;

public class TmsFeignConfig {

	@Bean
	public TmsRequestInterceptor tmsRequestInterceptor(TmsTokenStore tmsTokenStore) {
		return new TmsRequestInterceptor(tmsTokenStore);
	}
}
