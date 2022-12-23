package com.example.survey.controller;

import com.example.survey.dto.AverageScoreForSurveyDto;
import com.example.survey.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {
    private final StatisticsService statisticsService;
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @PostMapping("/survey-score")
    public ResponseEntity<String> getAverageScoreForSurvey(@RequestBody AverageScoreForSurveyDto averageScoreForSurveyDto){
        return ResponseEntity.ok(statisticsService.getAverageScoreForSurvey(averageScoreForSurveyDto));
    }

    @PostMapping("/company-survey-score/{id}")
    public ResponseEntity<String> getAverageScoreForCompanySurvey(@PathVariable Long id){
        return ResponseEntity.ok(statisticsService.getAverageScoreForCompanySurvey(id));
    }

    @PostMapping("/company-score/{id}")
    public ResponseEntity<String> getAverageScoreForCompany(@PathVariable Long id){
        return ResponseEntity.ok(statisticsService.getAverageScoreForCompany(id));
    }
}
