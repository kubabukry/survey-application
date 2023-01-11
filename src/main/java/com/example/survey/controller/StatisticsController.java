package com.example.survey.controller;

import static com.example.survey.mapper.ScoreMapper.mapScoreToScoreResponseDto;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.survey.dto.AverageScoreForSurveyDto;
import com.example.survey.dto.ScoreResponseDto;
import com.example.survey.service.StatisticsService;

@RestController
public class StatisticsController {
    private final StatisticsService statisticsService;
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @PostMapping("/survey-score")
    @ResponseBody
    public ScoreResponseDto getAverageScoreForSurvey(@Valid @RequestBody AverageScoreForSurveyDto averageScoreForSurveyDto){
        return mapScoreToScoreResponseDto(statisticsService.getAverageScoreForSurvey(averageScoreForSurveyDto));
    }

    @PostMapping("/company-survey-score/{id}")
    @ResponseBody
    public ScoreResponseDto getAverageScoreForCompanySurvey(@PathVariable Long id){
        return mapScoreToScoreResponseDto(statisticsService.getAverageScoreForCompanySurvey(id));
    }

    @PostMapping("/company-score/{id}")
    @ResponseBody
    public ScoreResponseDto getAverageScoreForCompany(@PathVariable Long id){
        return mapScoreToScoreResponseDto(statisticsService.getAverageScoreForCompany(id));
    }
}
