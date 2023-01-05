package com.example.survey.dto;

import com.example.survey.validation.ValidDescription;
import com.example.survey.validation.ValidName;

import javax.validation.constraints.NotNull;

public record CategoryDto(
        @NotNull
        Long id,
        @ValidDescription
        String description,
        @ValidName
        String name) {
}
