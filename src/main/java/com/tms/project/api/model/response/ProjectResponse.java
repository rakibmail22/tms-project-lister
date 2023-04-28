package com.tms.project.api.model.response;

import java.io.Serial;
import java.io.Serializable;

public record ProjectResponse(String name, String status, String sourceLang,
                              String targetLang) implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
}
