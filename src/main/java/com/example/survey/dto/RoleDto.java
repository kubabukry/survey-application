package com.example.survey.dto;

import javax.validation.constraints.NotNull;

import com.example.survey.validation.ValidRoleName;

public record RoleDto(
        @NotNull
        Long id,
        @ValidRoleName
        String name) {
}
