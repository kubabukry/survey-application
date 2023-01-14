package com.example.survey.dto;

public record LoginDto(String username,
                       String roleName,
                       Boolean isCompany) {
}
