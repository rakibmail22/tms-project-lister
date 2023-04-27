package com.tms.project.api.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserRequest(@NotEmpty @Size(min = 1, max = 128) String username,
                          @NotEmpty  @Size(min = 1, max = 128) String password) {
}