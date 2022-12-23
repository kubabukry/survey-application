package com.example.survey.dto;

import com.example.survey.validation.ValidCategoryDescription;
import com.example.survey.validation.ValidCategoryName;

public record CategoryCreationDto(
        @ValidCategoryDescription
        String description,
        @ValidCategoryName
        String name) {
}
