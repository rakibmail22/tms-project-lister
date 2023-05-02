package com.tms.project.tmsclient.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

public record TmsAuthToken(String token, ZonedDateTime expires) implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
}
