package com.example.survey.dto;

import com.example.survey.validation.ValidAddress;
import com.example.survey.validation.ValidName;
import com.example.survey.validation.ValidNip;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public record CompanyCreationDto(

        @ValidName
        String name,

        @ValidAddress
        String address,

        @ValidNip
        Long nip,

        @NotNull
        Long idUser) {
}
