package com.example.survey.controller;

import com.example.survey.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
}
