package com.tms.project.tmsclient;

import com.tms.project.tmsclient.config.TmsFeignConfig;
import com.tms.project.tmsclient.model.TmsProjectResponsePage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
		name = "${tms.client.api.name}",
		url = "${tms.client.api.url}",
		configuration = TmsFeignConfig.class)
public interface TmsFeignClient {

	@GetMapping(value = "/projects")
	TmsProjectResponsePage getProjects(@RequestParam(defaultValue = "0") int pageNumber,
	                                   @RequestParam(defaultValue = "1") int pageSize);
}