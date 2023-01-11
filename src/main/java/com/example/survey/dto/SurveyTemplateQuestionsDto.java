package com.example.survey.dto;

import com.example.survey.validation.ValidDescription;
import com.example.survey.validation.ValidName;

import javax.validation.constraints.NotNull;
import java.util.List;

public record SurveyTemplateQuestionsDto(@NotNull
                                         Long id,
                                         @ValidName
                                         String title,
                                         @ValidDescription
                                         String description,
                                         @NotNull
                                         CategoryDto category,

                                         //todo czy moze byc Null?
                                         List<QuestionDto> questionList) {
}
