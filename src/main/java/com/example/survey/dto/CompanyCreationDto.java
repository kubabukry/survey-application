package com.example.survey.dto;

import javax.validation.constraints.NotNull;

import com.example.survey.validation.ValidAddress;
import com.example.survey.validation.ValidName;
import com.example.survey.validation.ValidNip;


public record CompanyCreationDto(

        @ValidName
        String name,

        @ValidAddress
        String address,

        @ValidNip
        String nip,

        @NotNull
        Long idUser) {
}
