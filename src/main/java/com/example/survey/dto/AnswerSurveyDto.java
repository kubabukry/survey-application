package com.example.survey.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public record AnswerSurveyDto(
        @NotNull
        Long idCompanySurvey,
        @NotNull
        Long idUser,
        List<AnswerBodyDto> answers) {
}
