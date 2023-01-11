package com.example.survey.mapper;

import com.example.survey.dto.SurveyTemplateDto;
import com.example.survey.dto.SurveyTemplateQuestionsDto;
import com.example.survey.model.SurveyTemplate;

import static com.example.survey.mapper.QuestionMapper.mapQuestionListToQuestionDtoList;
import static com.example.survey.mapper.CategoryMapper.mapCategoryToCategoryDto;

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

    public static List<SurveyTemplateQuestionsDto> mapSurveyTemplateListToSurveyTemplateQuestionsDtoList(List<SurveyTemplate> surveyTemplateList){
        return surveyTemplateList.stream()
                .map(surveyTemplate -> new SurveyTemplateQuestionsDto(
                        surveyTemplate.getId(),
                        surveyTemplate.getTitle(),
                        surveyTemplate.getDescription(),
                        mapCategoryToCategoryDto(surveyTemplate.getCategory()),
                        mapQuestionListToQuestionDtoList(surveyTemplate.getQuestionList())
                ))
                .collect(Collectors.toList());
    }
}
