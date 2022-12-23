package com.example.survey.dto;

import javax.validation.constraints.NotBlank;

public record RegisteredUserChangePasswordDto(
        Long id,
        @NotBlank(message = "password is mandatory")
        String password) {
}
