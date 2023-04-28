package com.tms.project.api.endpoint;

import com.tms.project.webclient.TmsAuthFeignClient;
import com.tms.project.webclient.TmsFeignClient;
import com.tms.project.webclient.model.TmsAuthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiV1Controller
@RequiredArgsConstructor
public class ListProjectEndpoint {

	private final TmsFeignClient tmsFeignClient;

	@GetMapping(value = "/projects")
	public ResponseEntity<?> listProjects() {
		return ResponseEntity.ok(tmsFeignClient.getProjects());
	}
}