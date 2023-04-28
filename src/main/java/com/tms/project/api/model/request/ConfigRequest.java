package com.tms.project.api.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;

public record ConfigRequest(@NotEmpty @Size(min = 1, max = 128) String username,
                            @NotEmpty @Size(min = 1, max = 128) String password) implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
}