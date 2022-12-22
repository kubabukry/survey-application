package com.example.survey.mapper;

import com.example.survey.dto.CompanySurveyDto;
import com.example.survey.model.CompanySurvey;

public class CompanySurveyMapper {
    public static CompanySurveyDto mapCreateCompanyToCompanySurveyDto(CompanySurvey companySurvey){
        return new CompanySurveyDto(
                companySurvey.getId(),
                companySurvey.getSurveyTemplate().getId(),
                companySurvey.getCompany().getId(),
                companySurvey.getIsHidden()
        );
    }
}
