package com.example.survey.dto;

import javax.validation.constraints.NotNull;

import com.example.survey.validation.ValidAddress;
import com.example.survey.validation.ValidName;
import com.example.survey.validation.ValidNip;

public record CompanyDto(
        @NotNull
        Long id,
        @ValidName
        String name,
        @ValidNip
        String nip,
        @ValidAddress
        String address,
        @NotNull
        Boolean isVerified,
        @NotNull
        Long idUser) {
}
