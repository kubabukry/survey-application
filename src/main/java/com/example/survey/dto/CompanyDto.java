package com.example.survey.dto;

public record CompanyDto(
        Long id,
        String name,
        Long nip,
        String address,
        Boolean isVerified,
        Long idUser) {
}
