package com.tms.project.api.service;

import com.tms.project.api.UserRequest;
import com.tms.project.api.UserResponse;
import org.springframework.data.domain.Page;

public interface UserService {

	Page<UserResponse> getList(int offset, int limit);

	UserResponse create(UserRequest userRequest);

	UserResponse update(String userId, UserRequest userRequest);
}
