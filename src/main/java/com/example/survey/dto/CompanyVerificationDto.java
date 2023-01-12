package com.example.survey.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CompanyVerificationDto(
        @NotNull
        Long id,
        @NotNull
        Boolean isVerified) {
}
