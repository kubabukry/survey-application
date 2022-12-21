package com.example.survey.dto;

import java.util.List;

public record SurveyTemplateDto(
        Long id,
        String title,
        String description,
        Long idCategory,
        List<Long> idQuestionList) {
}
