package com.tms.project.api.endpoint;

import com.tms.project.api.model.response.ProjectResponse;
import com.tms.project.api.service.GetProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@ApiV1Controller
@RequiredArgsConstructor
public class ListProjectEndpoint {

	private final GetProjectService getProjectService;

	@GetMapping(value = "/projects")
	public Page<ProjectResponse> listProjects(@RequestParam(defaultValue = "0") int pageNumber) {
		return getProjectService.getList(pageNumber);
	}
}