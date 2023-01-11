package com.example.survey.controller;

import static com.example.survey.mapper.CompanySurveyMapper.mapCompanySurveyToCompanySurveyDto;
import static com.example.survey.mapper.SurveyAnswerMapper.mapSurveyAnswerToSurveyAnswerDto;
import static com.example.survey.mapper.SurveyTemplateMapper.mapSurveyTemplateListToSurveyTemplateDtoList;
import static com.example.survey.mapper.SurveyTemplateMapper.mapSurveyTemplateToSurveyTemplateDto;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.survey.dto.AnswerQuestionDto;
import com.example.survey.dto.AnswerSurveyDto;
import com.example.survey.dto.CompanySurveyDto;
import com.example.survey.dto.CompanySurveyVisibilityDto;
import com.example.survey.dto.CreateCompanySurveyDto;
import com.example.survey.dto.SurveyAnswerDto;
import com.example.survey.dto.SurveyDto;
import com.example.survey.dto.SurveyTemplateAddQuestionDto;
import com.example.survey.dto.SurveyTemplateCreationDto;
import com.example.survey.dto.SurveyTemplateDto;
import com.example.survey.model.SurveyTemplate;
import com.example.survey.service.SurveyService;

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

    @PutMapping("/answer-survey")
    public void answerSurvey(@RequestBody AnswerSurveyDto answerSurveyDto){
        surveyService.answerSurvey(answerSurveyDto);
    }
}