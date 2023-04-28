package com.tms.project.api.service.converter;

import com.tms.project.api.model.response.ProjectResponse;
import com.tms.project.tmsclient.model.TmsProjectResponse;
import org.springframework.stereotype.Component;

@Component
public class TmsProjectConverter {

	public ProjectResponse convert(TmsProjectResponse tmsProjectResponse) {
		return new ProjectResponse(
				tmsProjectResponse.name(),
				tmsProjectResponse.status(),
				tmsProjectResponse.sourceLang(),
				String.join(",", tmsProjectResponse.targetLangs())
		);
	}
}
