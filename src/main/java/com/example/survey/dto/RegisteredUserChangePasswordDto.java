package com.example.survey.dto;

import javax.validation.constraints.NotNull;

import com.example.survey.validation.PasswordComplexity;

public record RegisteredUserChangePasswordDto(
        @NotNull
        Long id,
        @PasswordComplexity
        String password) {
}
