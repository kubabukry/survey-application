package com.example.survey.dto;

import com.example.survey.validation.ValidName;
import com.example.survey.validation.ValidRoleName;

import javax.validation.constraints.NotNull;

public record RoleDto(
        @NotNull
        Long id,
        @ValidRoleName
        String name) {
}
