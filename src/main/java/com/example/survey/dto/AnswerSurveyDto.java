package com.example.survey.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public record AnswerSurveyDto(
        @NotNull
        Long idCompanySurvey,
        @NotNull
        Long idUser,
        List<AnswerBodyDto> answerBodyDtoList) {
}
