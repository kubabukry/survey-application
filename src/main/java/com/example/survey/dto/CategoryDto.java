package com.example.survey.dto;

import javax.validation.constraints.NotNull;

import com.example.survey.validation.ValidDescription;
import com.example.survey.validation.ValidName;

public record CategoryDto(
        @NotNull
        Long id,
        @ValidDescription
        String description,
        @ValidName
        String name) {
}
