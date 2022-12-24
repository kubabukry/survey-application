package com.example.survey.dto;

import com.example.survey.validation.ValidDescription;
import com.example.survey.validation.ValidName;

public record CategoryCreationDto(
        @ValidDescription
        String description,
        @ValidName
        String name) {
}
