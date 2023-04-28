package com.tms.project.api.endpoint;

import com.tms.project.webclient.TmsAuthFeignClient;
import com.tms.project.webclient.TmsFeignClient;
import com.tms.project.webclient.model.TmsAuthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ListProjectEndpoint {

	private final TmsFeignClient tmsFeignClient;

	private final TmsAuthFeignClient tmsAuthFeignClient;

	@GetMapping(value = "/api/v1/auth")
	public ResponseEntity<?> getAuthToken() {
		return ResponseEntity.ok(tmsAuthFeignClient.login(new TmsAuthRequest("rakibmail22", "HelloH!6HelloH!6")));
	}

	@GetMapping(value = "/api/v1/projects")
	public ResponseEntity<?> listProjects() {
		return ResponseEntity.ok(tmsFeignClient.getProjects());
	}
}