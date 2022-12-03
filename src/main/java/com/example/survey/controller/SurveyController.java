package com.example.survey.controller;

import com.example.survey.model.SurveyTemplate;
import com.example.survey.service.SurveyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SurveyController {
    private final SurveyService surveyService;
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping("/surveytemplates")
    public List<SurveyTemplate> getSurveyTemplates(){
        return surveyService.getSurveyTemplates();
    }

    @PostMapping("/surveytemplates")
    public SurveyTemplate createSurveyTemplate(@RequestBody SurveyTemplate surveyTemplate){
        return surveyService.createSurveyTemplate(surveyTemplate);
    }
}
