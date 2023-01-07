package com.example.survey.controller;

import com.example.survey.dto.*;
import com.example.survey.model.SurveyTemplate;
import com.example.survey.service.SurveyService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.survey.mapper.SurveyTemplateMapper.mapSurveyTemplateListToSurveyTemplateDtoList;
import static com.example.survey.mapper.SurveyTemplateMapper.mapSurveyTemplateToSurveyTemplateDto;
import static com.example.survey.mapper.CompanySurveyMapper.mapCompanySurveyToCompanySurveyDto;
import static com.example.survey.mapper.SurveyAnswerMapper.mapSurveyAnswerToSurveyAnswerDto;

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

    @PostMapping("/survey-templates")
    public SurveyTemplate createSurveyTemplate(@Valid @RequestBody SurveyTemplateCreationDto surveyTemplateCreationDto){
        return surveyService.createSurveyTemplate(surveyTemplateCreationDto);
    }

    @PutMapping("/survey-templates")
    public SurveyTemplateDto updateSurveyTemplate(@Valid @RequestBody SurveyTemplateDto surveyTemplateDto){
        return mapSurveyTemplateToSurveyTemplateDto(surveyService.updateSurveyTemplate(surveyTemplateDto));
    }

    @PutMapping("/survey-templates/add-question")
    public SurveyTemplateDto addQuestionToSurveyTemplate(@Valid @RequestBody SurveyTemplateAddQuestionDto surveyTemplateAddQuestionDto){
        return mapSurveyTemplateToSurveyTemplateDto(surveyService.addQuestionToSurveyTemplate(surveyTemplateAddQuestionDto));
    }

    @PostMapping("/company-survey")
    public CompanySurveyDto createCompanySurvey(@Valid @RequestBody CreateCompanySurveyDto createCompanySurveyDto){
        return mapCompanySurveyToCompanySurveyDto(surveyService.createCompanySurvey(createCompanySurveyDto));
    }

    @PutMapping("/company-survey/set-visibility")
    public CompanySurveyDto setCompanySurveyVisibility(@Valid @RequestBody CompanySurveyVisibilityDto companySurveyVisibilityDto){
        return mapCompanySurveyToCompanySurveyDto(surveyService.setCompanySurveyVisibility(companySurveyVisibilityDto));
    }

    @PostMapping("/survey-answer")
    public SurveyAnswerDto answerQuestion(@Valid @RequestBody AnswerQuestionDto answerQuestionDto){
        return mapSurveyAnswerToSurveyAnswerDto(surveyService.answerQuestion(answerQuestionDto));
    }

    @GetMapping("/survey-answer/{id}")
    public SurveyAnswerDto getSingleSurveyAnswer(@PathVariable Long id){
        return mapSurveyAnswerToSurveyAnswerDto(surveyService.getSingleSurveyAnswer(id));
    }

    @GetMapping("/company-survey/{id}")
    public SurveyDto getCompanySurvey(@PathVariable Long id){
        return surveyService.getCompanySurvey(id);
    }

    @DeleteMapping("/survey-templates/{id}")
    public void deleteSurveyTemplate(@PathVariable Long id){
        surveyService.deleteSurveyTemplate(id);
    }
}