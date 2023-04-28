package com.tms.project.tmsclient.model;

import java.io.Serial;
import java.io.Serializable;

public record TmsAuthRequest(String userName, String password) implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
}
