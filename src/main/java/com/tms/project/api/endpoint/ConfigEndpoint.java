package com.tms.project.api.endpoint;

import com.tms.project.api.model.request.ConfigRequest;
import com.tms.project.api.model.response.ConfigResponse;
import com.tms.project.api.service.ConfigService;
import com.tms.project.api.service.GetConfigService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@ApiV1Controller
@RequiredArgsConstructor
public class ConfigEndpoint {

	private final ConfigService configService;

	private final GetConfigService getConfigService;

	@GetMapping(value = "/config")
	public ConfigResponse get() {
		return getConfigService.getConfig();
	}

	@GetMapping(value = "/config/{id}")
	public ConfigResponse get(@PathVariable String id) {
		return getConfigService.get(id);
	}

	@PostMapping(value = "/config")
	public ResponseEntity<ConfigResponse> create(@Valid @RequestBody ConfigRequest configRequest,
	                                             HttpServletRequest request) {
		ConfigResponse configResponse = configService.create(configRequest);

		return ResponseEntity.created(getLocationUri(request, configResponse.id()))
		                     .body(configResponse);
	}

	@PutMapping(value = "/config/{id}")
	public ConfigResponse update(@PathVariable String id,
	                             @Valid @RequestBody ConfigRequest configRequest) {
		return configService.update(id, configRequest);
	}

	private URI getLocationUri(HttpServletRequest request, String id) {
		return UriComponentsBuilder.fromUriString(request.getRequestURI())
		                           .path("/{id}")
		                           .buildAndExpand(id)
		                           .toUri();
	}
}
