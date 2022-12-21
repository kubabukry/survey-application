package com.example.survey.mapper;

import com.example.survey.dto.AnswerQuestionDto;
import com.example.survey.model.SurveyAnswer;

public class SurveyAnswerMapper {

    public static AnswerQuestionDto mapSurveyAnswerToAnswerQuestionDto(SurveyAnswer surveyAnswer){
        return new AnswerQuestionDto(surveyAnswer.getQuestion().getId(), surveyAnswer.getRating());
    }
}
