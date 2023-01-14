package com.example.survey.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryScoreDto {
    private String companyName;
    private Long idTemplate;
    private String score;
}
