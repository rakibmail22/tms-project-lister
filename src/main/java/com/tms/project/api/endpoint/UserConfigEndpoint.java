package com.tms.project.api.endpoint;

import com.tms.project.api.UserRequest;
import com.tms.project.api.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserConfigEndpoint {

	@GetMapping(value = "/users")
	public Page<UserResponse> list() {
		return null;
	}

	@PostMapping
	public UserResponse create(@RequestBody UserRequest userRequest) {
		return null;
	}

	@PutMapping
	public UserResponse update() {
		return null;
	}

}
