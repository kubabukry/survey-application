package com.example.survey.dto;

public record RegisteredUserRegistrationDto(
        String login,

        String password,
        String name,
        String mail
) {
}
