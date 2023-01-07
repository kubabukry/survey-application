package com.example.survey.dto;

import javax.validation.constraints.NotNull;

public record AnswerBodyDto(
        @NotNull
        Long idQuestion,
        @NotNull
        Integer rating) {
}
