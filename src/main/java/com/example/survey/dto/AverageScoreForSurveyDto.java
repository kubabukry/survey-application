package com.example.survey.dto;

import javax.validation.constraints.NotNull;

public record AverageScoreForSurveyDto(
        @NotNull
        Long idUser,
        @NotNull
        Long idCompanySurvey) {
}
