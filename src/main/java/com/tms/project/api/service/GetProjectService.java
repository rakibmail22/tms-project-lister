package com.tms.project.api.service;

import com.tms.project.api.model.request.ConfigRequest;
import com.tms.project.api.model.response.ProjectResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GetProjectService {

	Page<ProjectResponse> getList(int pageNumber);
}
