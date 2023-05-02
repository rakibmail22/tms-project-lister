package com.tms.project.api.service;

import com.tms.project.api.model.response.ProjectResponse;
import org.springframework.data.domain.Page;

public interface GetProjectService {

	Page<ProjectResponse> getList(int pageNumber, int pageSize);
}
