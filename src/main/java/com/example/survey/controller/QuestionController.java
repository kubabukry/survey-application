package com.example.survey.controller;

import static com.example.survey.mapper.QuestionMapper.mapQuestionListToQuestionDtoList;
import static com.example.survey.mapper.QuestionMapper.mapQuestionToQuestionDto;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.survey.dto.QuestionCreationDto;
import com.example.survey.dto.QuestionDto;
import com.example.survey.service.QuestionService;

@RestController
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/questions")
    public List<QuestionDto> getQuestions(){
        return mapQuestionListToQuestionDtoList(questionService.getQuestions());
    }

    @GetMapping("/questions/{id}")
    public QuestionDto getSingleQuestion(@PathVariable Long id){
        return mapQuestionToQuestionDto(questionService.getSingleQuestion(id));
    }
    @PostMapping("/questions")
    public QuestionDto createQuestion(@Valid @RequestBody QuestionCreationDto questionCreationDto){
        return mapQuestionToQuestionDto(questionService.createQuestion(questionCreationDto));
    }
    @PutMapping("/questions/{id}")
    public QuestionDto updateQuestion(@Valid @RequestBody QuestionDto questionDto){
        return mapQuestionToQuestionDto(questionService.updateQuestion(questionDto));
    }

    @DeleteMapping("/questions/{id}")
    public void deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id);
    }
}
