package com.tms.project.api.endpoint;

import com.tms.project.api.model.UserRequest;
import com.tms.project.api.model.UserResponse;
import com.tms.project.api.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UserConfigEndpoint {

	private static final int MAX_PAGE_SIZE = 20;

	private final UserService userService;

	@GetMapping(value = "/api/v1/users")
	public Page<UserResponse> list(@RequestParam(defaultValue = "0") int offset) {
		return userService.getList(offset, MAX_PAGE_SIZE);
	}

	@GetMapping(value = "/api/v1/user/{id}")
	public UserResponse get(@PathVariable String id) {
		return userService.get(id);
	}

	@PostMapping(value = "/api/v1/user")
	public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest, HttpServletRequest request) {
		UserResponse userResponse = userService.create(userRequest);

		return ResponseEntity.created(getLocationUri(request, userResponse.id()))
		                     .body(userResponse);
	}

	@PutMapping(value = "/api/v1/user/{id}")
	public UserResponse update(@PathVariable String id,
	                           @RequestBody UserRequest userRequest) {
		return userService.update(id, userRequest);
	}

	private URI getLocationUri(HttpServletRequest request, String id) {
		return UriComponentsBuilder.fromUriString(request.getRequestURI())
		                           .path("/{id}")
		                           .buildAndExpand(id)
		                           .toUri();
	}
}
