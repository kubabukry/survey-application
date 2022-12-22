package com.example.survey.mapper;
import com.example.survey.dto.SurveyAnswerDto;
import com.example.survey.model.SurveyAnswer;

public class SurveyAnswerMapper {

    public static SurveyAnswerDto mapSurveyAnswerToSurveyAnswerDto(SurveyAnswer surveyAnswer){
        return new SurveyAnswerDto(
                surveyAnswer.getId(),
                surveyAnswer.getRating(),
                surveyAnswer.getCompanySurvey().getId(),
                surveyAnswer.getIdUser().getId(),
                surveyAnswer.getQuestion().getId()
        );
    }
}
