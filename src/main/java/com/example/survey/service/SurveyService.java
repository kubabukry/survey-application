package com.example.survey.service;

import com.example.survey.repository.CompanySurveyRepository;
import com.example.survey.repository.SurveyAnswerRepository;
import com.example.survey.repository.SurveyTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyAnswerRepository surveyAnswerRepository;

    private CompanyService companyService;

    private QuestionService questionService;

    //CompanySurveyTemplateRepository na diagramie?
    private final CompanySurveyRepository companySurveyRepository;

    private RegisteredUserService registeredUserService;

    private final SurveyTemplateRepository surveyTemplateRepository;

    private CategoryService categoryService;

    //createSurveyTemplate()
    //updateSurveyTemplate()
    //deleteSurveyTemplate()
    //getSurveyTemplate()
    //addSurveyTemplate()
    //getSurveyAnswer()
    //addSurveyToCompany()
    //setCompanySurveyVisibility()
}
