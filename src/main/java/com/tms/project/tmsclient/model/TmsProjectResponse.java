package com.tms.project.tmsclient.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record TmsProjectResponse(String name, String status, String sourceLang,
                                 List<String> targetLangs) implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
}
