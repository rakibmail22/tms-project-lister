package com.tms.project.api.service;

import com.tms.project.api.model.response.ProjectResponse;
import com.tms.project.api.service.converter.TmsProjectConverter;
import com.tms.project.tmsclient.TmsFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProjectServiceImpl implements GetProjectService {

	private final TmsFeignClient tmsFeignClient;

	private final TmsProjectConverter tmsProjectConverter;

	@Override
	public Page<ProjectResponse> getList(int pageNumber) {
		return tmsFeignClient.getProjects(pageNumber)
		                     .map(tmsProjectConverter::convert);
	}
}
