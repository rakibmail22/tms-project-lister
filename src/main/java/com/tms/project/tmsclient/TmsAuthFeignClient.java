package com.tms.project.tmsclient;

import com.tms.project.tmsclient.model.TmsAuthRequest;
import com.tms.project.tmsclient.model.TmsAuthToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "tmsApiAuthClient", url = "https://cloud.memsource.com/web/api2/v1/auth")
public interface TmsAuthFeignClient {

	@GetMapping(value = "/login")
	TmsAuthToken login(@RequestBody TmsAuthRequest authRequest);
}
