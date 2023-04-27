package com.tms.project.api.service;

import com.tms.project.api.model.UserRequest;
import com.tms.project.api.model.UserResponse;
import org.springframework.data.domain.Page;

public interface UserService {

	UserResponse create(UserRequest userRequest);

	UserResponse update(String userId, UserRequest userRequest);
}
