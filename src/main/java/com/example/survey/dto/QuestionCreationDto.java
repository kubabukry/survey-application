package com.example.survey.dto;

import com.example.survey.validation.ValidName;

public record QuestionCreationDto(@ValidName String name){}

