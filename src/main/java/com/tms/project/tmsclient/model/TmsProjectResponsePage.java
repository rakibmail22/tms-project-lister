package com.tms.project.tmsclient.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record TmsProjectResponsePage(int pageNumber, List<TmsProjectResponse> content,
                                     int pageSize, int totalPages,
                                     int totalElements, int numberOfElements) implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
}
