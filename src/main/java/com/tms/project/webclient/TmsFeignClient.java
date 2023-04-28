package com.tms.project.webclient;

import com.tms.project.webclient.config.TmsFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
		name = "tmsApiClient",
		url = "https://cloud.memsource.com/web/api2/v1",
		configuration = TmsFeignConfig.class)
public interface TmsFeignClient {

	@GetMapping(value = "/projects")
	String getProjects();
}