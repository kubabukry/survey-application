package com.example.survey.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.example.survey.dto.SurveyTemplateDto;
import com.example.survey.model.SurveyTemplate;

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

    public static List<SurveyTemplateDto> mapSurveyTemplateListToSurveyTemplateDtoList(List<SurveyTemplate> surveyTemplateList){
        return surveyTemplateList.stream()
                .map(surveyTemplate -> new SurveyTemplateDto(
                        surveyTemplate.getId(),
                        surveyTemplate.getTitle(),
                        surveyTemplate.getDescription(),
                        surveyTemplate.getCategory().getId(),
                        surveyTemplate.getQuestionList().stream()
                                .map(question -> question.getId())
                                .collect(Collectors.toList())
                )).collect(Collectors.toList());
    }
}
