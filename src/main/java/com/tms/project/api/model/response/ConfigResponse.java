package com.tms.project.api.model.response;

import java.io.Serial;
import java.io.Serializable;

public record ConfigResponse(String id, String username, String password) implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
}