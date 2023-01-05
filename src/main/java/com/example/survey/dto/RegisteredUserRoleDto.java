package com.example.survey.dto;

import javax.validation.constraints.NotNull;

public record RegisteredUserRoleDto(
        @NotNull
        Long userId,
        @NotNull
        Long roleId) {
}
