package com.example.survey.dto;

public record AnswerQuestionDto(Long idUser, Long idQuestion, Long idCompanySurvey, Integer rating) {
}
