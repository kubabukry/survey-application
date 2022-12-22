package com.example.survey.dto;

public record SurveyAnswerDto(Long id,
                              Integer rating,
                              Long idCompanySurvey,
                              Long idUser,
                              Long idQuestion) {
}
