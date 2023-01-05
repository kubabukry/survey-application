package com.example.survey.mapper;

import com.example.survey.dto.ScoreResponseDto;

public class ScoreMapper {

    public static ScoreResponseDto mapScoreToScoreResponseDto(String score){
        return new ScoreResponseDto(score);
    }
}
