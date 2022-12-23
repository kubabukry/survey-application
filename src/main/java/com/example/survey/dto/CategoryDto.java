package com.example.survey.dto;

import com.example.survey.validation.ValidCategoryDescription;
import com.example.survey.validation.ValidCategoryName;

import javax.validation.constraints.NotNull;

public record CategoryDto(
        @NotNull
        Long id,
        @ValidCategoryDescription
        String description,
        @ValidCategoryName
        String name) {
}
