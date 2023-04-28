package com.tms.project.webclient.model;

import java.time.ZonedDateTime;

public record TmsAuthToken(String token, ZonedDateTime expires) {
}
