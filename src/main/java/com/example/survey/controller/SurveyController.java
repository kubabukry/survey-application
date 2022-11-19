package com.example.survey.controller;

import com.example.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;
}
