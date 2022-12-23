package com.example.survey.dto;

import javax.validation.constraints.NotBlank;

public record RegisteredUserDto(Long id,
                                @NotBlank(message = "login is mandatory")
                                String login,
                                @NotBlank(message = "name is mandatory")
                                String name,
                                @NotBlank(message = "mail is mandatory")
                                String mail,
                                @NotBlank(message = "isActive is mandatory")
                                Boolean isActive,
                                @NotBlank(message = "role is mandatory")
                                String roleName) {
}
