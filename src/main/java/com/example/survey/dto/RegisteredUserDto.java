package com.example.survey.dto;
public record RegisteredUserDto(Long id,
                                String login,
                                String name,
                                String mail,
                                Boolean isActive,
                                String roleName) {
}
