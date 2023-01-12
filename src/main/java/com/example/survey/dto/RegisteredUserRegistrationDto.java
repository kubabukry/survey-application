package com.example.survey.dto;

import com.example.survey.validation.PasswordComplexity;
import com.example.survey.validation.ValidLogin;
import com.example.survey.validation.ValidMail;
import com.example.survey.validation.ValidUserName;

import javax.validation.constraints.Pattern;

public record RegisteredUserRegistrationDto(
        @ValidLogin
        String login,
        @PasswordComplexity
        String password,
        @ValidUserName
        String name,
        @ValidMail
        String mail
) {
}
