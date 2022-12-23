package com.example.survey.controller;

import com.example.survey.dto.AverageScoreForSurveyDto;
import com.example.survey.dto.ScoreResponseDto;
import com.example.survey.service.StatisticsService;
import org.springframework.web.bind.annotation.*;

import static com.example.survey.mapper.ScoreMapper.mapScoreToScoreResponseDto;

@RestController
public class StatisticsController {
    private final StatisticsService statisticsService;
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @PostMapping("/survey-score")
    @ResponseBody
    public ScoreResponseDto getAverageScoreForSurvey(@RequestBody AverageScoreForSurveyDto averageScoreForSurveyDto){
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
