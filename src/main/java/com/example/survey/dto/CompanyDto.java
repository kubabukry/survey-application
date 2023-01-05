package com.example.survey.dto;

import com.example.survey.validation.ValidAddress;
import com.example.survey.validation.ValidName;
import com.example.survey.validation.ValidNip;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CompanyDto(
        @NotNull
        Long id,
        @ValidName
        String name,
        @ValidNip
        Long nip,
        @ValidAddress
        String address,
        @NotNull
        Boolean isVerified,
        @NotNull
        Long idUser) {
}
