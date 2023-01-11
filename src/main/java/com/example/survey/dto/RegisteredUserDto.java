package com.example.survey.dto;

import javax.validation.constraints.NotNull;

import com.example.survey.validation.ValidLogin;
import com.example.survey.validation.ValidMail;
import com.example.survey.validation.ValidUserName;

public record RegisteredUserDto(Long id,
                                @ValidLogin
                                String login,
                                @ValidUserName
                                String name,

                                @ValidMail
                                String mail,
                                @NotNull
                                Boolean isActive,
                                @NotNull(message = "role is mandatory")
                                String roleName) {
}
