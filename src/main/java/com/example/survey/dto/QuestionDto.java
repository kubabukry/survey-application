package com.example.survey.dto;

import javax.validation.constraints.NotNull;

import com.example.survey.validation.ValidName;

public record QuestionDto(
        @NotNull
        Long id,
        @ValidName
        String name) {
}
