package com.tms.project.api;

public record UserRequest(String username, String password,
                          String firstName, String lastName,
                          String email) {
}