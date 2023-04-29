package com.tms.project.api.service;

import com.tms.project.api.model.response.ProjectResponse;
import com.tms.project.api.service.converter.TmsProjectConverter;
import com.tms.project.tmsclient.TmsFeignClient;
import com.tms.project.tmsclient.model.TmsProjectResponsePage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProjectServiceImpl implements GetProjectService {

	private final TmsFeignClient tmsFeignClient;

	private final TmsProjectConverter tmsProjectConverter;

	@Override
	public Page<ProjectResponse> getList(int pageNumber, int pageSize) {

		TmsProjectResponsePage tmsProjectResponsePage = tmsFeignClient.getProjects(pageNumber, pageSize);

		List<ProjectResponse> projectResponses = tmsProjectResponsePage.content().stream()
		                                                               .map(tmsProjectConverter::convert)
		                                                               .toList();

		return new PageImpl<>(projectResponses,
		                      PageRequest.of(tmsProjectResponsePage.pageNumber(), tmsProjectResponsePage.pageSize()),
		                      tmsProjectResponsePage.totalElements());
	}
}
