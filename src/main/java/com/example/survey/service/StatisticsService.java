package com.example.survey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private SurveyService surveyService;

    private CompanyService companyService;

    //getCompanySurveyScore()
    //getAverageScoreForSurvey()
    //getAverageScoreForCompany()
}
