package com.example.survey.dto;

import javax.validation.constraints.NotNull;

public record CompanySurveyVisibilityDto(
        @NotNull
        Long id,
        @NotNull
        Boolean isHidden) {
}
