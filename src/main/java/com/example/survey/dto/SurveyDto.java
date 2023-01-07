package com.example.survey.dto;

import java.util.List;

public record SurveyDto(Long companySurveyId,
                        String companyName,
                        String categoryName,
                        List<QuestionDto> questions) {
}
