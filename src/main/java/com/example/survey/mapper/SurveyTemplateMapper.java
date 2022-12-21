package com.example.survey.mapper;

import com.example.survey.dto.SurveyTemplateDto;
import com.example.survey.model.SurveyTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class SurveyTemplateMapper {

    public static SurveyTemplateDto mapSurveyTemplateToSurveyTemplateDto(SurveyTemplate surveyTemplate){
        List<Long> questionIdList = surveyTemplate.getQuestionList()
                .stream()
                .map(question -> question.getId())
                .collect(Collectors.toList());

        return new SurveyTemplateDto(
                surveyTemplate.getId(),
                surveyTemplate.getTitle(),
                surveyTemplate.getDescription(),
                surveyTemplate.getCategory().getId(),
                questionIdList
        );
    }
}
