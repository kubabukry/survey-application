package com.example.survey.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record RegisteredUserActivationDto(
        @NotNull
        Long id,
        @NotNull
        Boolean isActive) {
}
