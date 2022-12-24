package com.example.survey.dto;

import javax.validation.constraints.NotNull;

public record CreateCompanySurveyDto(
        @NotNull
        Long idCompany,
        @NotNull
        Long idSurveyTemplate,
        @NotNull
        Boolean isHidden) {
}
