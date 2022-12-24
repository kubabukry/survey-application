package com.example.survey.dto;

import com.example.survey.validation.PasswordComplexity;
import javax.validation.constraints.NotNull;

public record RegisteredUserChangePasswordDto(
        @NotNull
        Long id,
        @PasswordComplexity
        String password) {
}
