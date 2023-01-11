package com.example.survey.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.example.survey.validation.ValidDescription;
import com.example.survey.validation.ValidName;

public record SurveyTemplateCreationDto(
        @ValidName
        String title,
        @ValidDescription
        String description,
        @NotNull
        Long idCategory,
        //todo moze byc tworzone z pustą liczbą pytań?
        List<Long> idQuestionList) {
}
