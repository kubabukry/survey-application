package com.example.survey.dto;

import com.example.survey.validation.ValidLogin;
import com.example.survey.validation.ValidMail;
import com.example.survey.validation.ValidUserName;

import javax.validation.constraints.*;

public record RegisteredUserDto(Long id,
                                @ValidLogin
                                String login,
                                @ValidUserName
                                String name,

                                @ValidMail
                                String mail,
                                //NotNull wystarczające dla Boolean
                                @NotNull
                                Boolean isActive,
                                //NotNull wystarczające dla roli, wyjątki są sprawdzane w serwisie
                                @NotNull
                                @NotBlank(message = "role is mandatory")
                                String roleName) {
}
