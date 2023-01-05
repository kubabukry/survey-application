package com.example.survey.dto;

import javax.validation.constraints.NotNull;

public record AnswerQuestionDto(
        @NotNull
        Long idUser,
        @NotNull
        Long idQuestion,
        @NotNull
        Long idCompanySurvey,
        //todo jakaś walidacja w stylu 1-5?
        @NotNull
        Integer rating) {
}
