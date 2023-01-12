package com.example.survey.controller;

import com.example.survey.dto.QuestionCreationDto;
import com.example.survey.dto.QuestionDto;
import com.example.survey.exception.ErrorResponse;
import com.example.survey.exception.LoginAlreadyInUseException;
import com.example.survey.exception.NoSuchQuestionExistsException;
import com.example.survey.exception.QuestionAlreadyExistsException;
import com.example.survey.model.Question;
import com.example.survey.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.survey.mapper.QuestionMapper.mapQuestionToQuestionDto;
import static com.example.survey.mapper.QuestionMapper.mapQuestionListToQuestionDtoList;

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
