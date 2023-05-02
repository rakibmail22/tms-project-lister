package com.tms.project.api.service;

import com.tms.project.api.model.response.ConfigResponse;
import org.springframework.data.domain.Page;

public interface GetConfigService {

	ConfigResponse get(String id);

	Page<ConfigResponse> getList(int offset, int limit);

	ConfigResponse getConfig();

	ConfigResponse findByUsername(String username);
}
