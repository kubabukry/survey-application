package com.example.survey.dto;

import javax.validation.constraints.NotNull;

public record SurveyTemplateAddQuestionDto(
        @NotNull
        Long idSurveyTemplate,
        @NotNull
        Long idQuestion) {
}
