package com.example.survey.dto;

import com.example.survey.validation.ValidRoleName;

public record RoleNameDto(@ValidRoleName String name) {
}
