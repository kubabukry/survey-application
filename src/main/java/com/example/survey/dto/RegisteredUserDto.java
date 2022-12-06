package com.example.survey.dto;

//zwraca podstawowe info o userze
public record RegisteredUserDto(Long id,
                                String login,
                                String name,
                                String mail,
                                Boolean isActive,
                                String roleName) {
}
