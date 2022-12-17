package com.example.survey.mapper;

import com.example.survey.dto.RegisteredUserDto;
import com.example.survey.dto.RegisteredUserRoleDto;
import com.example.survey.model.RegisteredUser;

import java.util.List;
import java.util.stream.Collectors;

public class RegisteredUserDtoMapper {

    public static List<RegisteredUserDto> mapRegisteredUserToRegisteredUserDtoList(List<RegisteredUser> registeredUserList){
        return registeredUserList.stream()
                .map(registeredUser -> new RegisteredUserDto(
                        registeredUser.getId(),
                        registeredUser.getLogin(),
                        registeredUser.getName(),
                        registeredUser.getMail(),
                        registeredUser.getIsActive(),
                        registeredUser.getRole().getName()))
                .collect(Collectors.toList());
    }

    public static RegisteredUserDto mapRegisteredUserToRegisteredUserDto(RegisteredUser registeredUser){
        return new RegisteredUserDto(
                registeredUser.getId(),
                registeredUser.getLogin(),
                registeredUser.getName(),
                registeredUser.getMail(),
                registeredUser.getIsActive(),
                registeredUser.getRole().getName());
    }

    //poki co zbedne
    public static RegisteredUserRoleDto mapRegisteredUserToRegisteredUserRoleDto(RegisteredUser registeredUser){
        return new RegisteredUserRoleDto(
                registeredUser.getId(),
                registeredUser.getRole().getId()
        );
    }
}
