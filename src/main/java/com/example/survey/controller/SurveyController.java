package com.example.survey.controller;

import com.example.survey.dto.AnswerQuestionDto;
import com.example.survey.dto.QuestionDto;
import com.example.survey.dto.SurveyTemplateCreationDto;
import com.example.survey.dto.SurveyTemplateDto;
import com.example.survey.exception.*;
import com.example.survey.model.SurveyTemplate;
import com.example.survey.service.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.example.survey.mapper.SurveyTemplateMapper.mapSurveyTemplateToSurveyTemplateDto;

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

    @ExceptionHandler(value = TitleAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleTitleAlreadyExistsException(TitleAlreadyExistsException e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(value = NoSuchCategoryExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoSuchCategoryExistsException(NoSuchCategoryExistsException e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = NoSuchSurveyTemplateExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoSuchSurveyTemplateExistsException(NoSuchSurveyTemplateExistsException e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = NoSuchQuestionExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoSuchQuestionExistsException(NoSuchQuestionExistsException e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
