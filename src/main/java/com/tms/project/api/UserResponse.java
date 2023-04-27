package com.tms.project.api;

public record UserResponse(String id, String username, String firstname, String lastName, String email,
                           String timezone, String role) {
}