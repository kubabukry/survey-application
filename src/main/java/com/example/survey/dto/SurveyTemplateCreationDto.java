package com.example.survey.dto;

import java.util.List;

public record SurveyTemplateCreationDto(String title,
                                        String description,
                                        Long idCategory,
                                        List<Long> idQuestionList) {
}
