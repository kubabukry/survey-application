package com.example.survey.dto;

public record RegisteredUserRegistrationDto(
    String login,
    String name,

    String mail,

    String password
) {
}
