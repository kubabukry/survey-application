package com.example.survey.controller;

import com.example.survey.dto.*;
import com.example.survey.model.SurveyTemplate;
import com.example.survey.service.SurveyService;
import org.springframework.web.bind.annotation.*;

import static com.example.survey.mapper.SurveyTemplateMapper.mapSurveyTemplateListToSurveyTemplateDtoList;
import static com.example.survey.mapper.SurveyTemplateMapper.mapSurveyTemplateToSurveyTemplateDto;
import static com.example.survey.mapper.CompanySurveyMapper.mapCreateCompanyToCompanySurveyDto;

import java.util.List;

@RestController
public class SurveyController {
    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping("/survey-templates")
    public List<SurveyTemplateDto> getSurveyTemplates(){
        return mapSurveyTemplateListToSurveyTemplateDtoList(surveyService.getSurveyTemplates());
    }

    @GetMapping("/survey-templates/{id}")
    public SurveyTemplateDto getSingleSurveyTemplate(@PathVariable Long id){
        return mapSurveyTemplateToSurveyTemplateDto(surveyService.getSingleSurveyTemplate(id));
    }


//    @PutMapping("/survey/answer")
//    public AnswerQuestionDto answerQuestion(@RequestBody AnswerQuestionDto answerQuestionDto){
//        return mapSurveyAnswerToAnswerQuestionDto(surveyService.answerQuestion(answerQuestionDto));
//    }

    @PostMapping("/survey-templates")
    public SurveyTemplate createSurveyTemplate(@RequestBody SurveyTemplateCreationDto surveyTemplateCreationDto){
        return surveyService.createSurveyTemplate(surveyTemplateCreationDto);
    }

    @PutMapping("/survey-templates")
    public SurveyTemplateDto updateSurveyTemplate(@RequestBody SurveyTemplateDto surveyTemplateDto){
        return mapSurveyTemplateToSurveyTemplateDto(surveyService.updateSurveyTemplate(surveyTemplateDto));
    }

    @PutMapping("/survey-templates/add-question")
    public SurveyTemplateDto addQuestionToSurveyTemplate(@RequestBody SurveyTemplateAddQuestionDto surveyTemplateAddQuestionDto){
        return mapSurveyTemplateToSurveyTemplateDto(surveyService.addQuestionToSurveyTemplate(surveyTemplateAddQuestionDto));
    }

    @PostMapping("/company-survey")
    public CompanySurveyDto createCompanySurvey(@RequestBody CreateCompanySurveyDto createCompanySurveyDto){
        return mapCreateCompanyToCompanySurveyDto(surveyService.createCompanySurvey(createCompanySurveyDto));
    }

    @DeleteMapping("/survey-templates/{id}")
    public void deleteSurveyTemplate(@PathVariable Long id){
        surveyService.deleteSurveyTemplate(id);
    }
}
