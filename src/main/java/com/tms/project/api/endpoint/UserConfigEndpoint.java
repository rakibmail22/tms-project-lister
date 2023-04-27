package com.tms.project.api.endpoint;

import com.tms.project.api.UserRequest;
import com.tms.project.api.UserResponse;
import com.tms.project.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserConfigEndpoint {

	private static final int MAX_PAGE_SIZE = 20;

	private final UserService userService;

	@GetMapping(value = "/users")
	public Page<UserResponse> list(@RequestParam(defaultValue = "0") int offset) {
		return userService.getList(offset, MAX_PAGE_SIZE);
	}

	@PostMapping(value = "/user")
	public UserResponse create(@RequestBody UserRequest userRequest) {
		return userService.create(userRequest);
	}

	@PutMapping(value = "/user/{id}")
	public UserResponse update(@PathVariable String id,
	                           @RequestBody UserRequest userRequest) {
		return userService.update(id, userRequest);
	}
}
