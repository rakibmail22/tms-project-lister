package com.tms.project.api.service;

import com.tms.project.api.model.UserResponse;
import org.springframework.data.domain.Page;

public interface GetUserService {

	UserResponse get(String id);

	Page<UserResponse> getList(int offset, int limit);
}
