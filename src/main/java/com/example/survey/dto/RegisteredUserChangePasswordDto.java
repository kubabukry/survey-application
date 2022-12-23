package com.example.survey.dto;

import com.example.survey.validation.PasswordComplexity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record RegisteredUserChangePasswordDto(
        @NotNull
        Long id,
        @PasswordComplexity
        String password) {
}
