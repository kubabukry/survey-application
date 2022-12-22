package com.example.survey.dto;

public record CompanyCreationDto(
        String name,
        String address,
        Long nip,
        Long idUser) {
}
