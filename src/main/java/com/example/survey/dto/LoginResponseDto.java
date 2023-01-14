package com.example.survey.dto;

public record LoginResponseDto(String id,
                               String username,
                               String roleName,
                               Boolean isCompany) {
}
