package com.example.survey.dto;

import com.example.survey.validation.ValidName;

import javax.validation.constraints.NotNull;

public record QuestionDto(
        @NotNull
        Long id,
        @ValidName
        String name) {
}
