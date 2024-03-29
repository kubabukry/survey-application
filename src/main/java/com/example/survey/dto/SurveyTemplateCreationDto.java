package com.example.survey.dto;

import com.example.survey.validation.ValidDescription;
import com.example.survey.validation.ValidName;

import javax.validation.constraints.NotNull;
import java.util.List;

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
