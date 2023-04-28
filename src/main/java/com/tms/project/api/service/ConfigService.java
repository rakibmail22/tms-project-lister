package com.tms.project.api.service;

import com.tms.project.api.model.request.ConfigRequest;
import com.tms.project.api.model.response.ConfigResponse;

public interface ConfigService {

	ConfigResponse create(ConfigRequest configRequest);

	ConfigResponse update(String userId, ConfigRequest configRequest);
}
