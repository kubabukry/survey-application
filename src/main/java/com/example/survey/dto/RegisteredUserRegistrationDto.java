package com.example.survey.dto;

import javax.validation.constraints.NotBlank;

public record RegisteredUserRegistrationDto(
        @NotBlank(message = "login is mandatory")
        String login,

        @NotBlank(message = "password is mandatory")
        String password,
        @NotBlank(message = "name is mandatory")
        String name,
        @NotBlank(message = "mail is mandatory")
        String mail
) {
}
