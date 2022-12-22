package com.example.survey.controller;

import com.example.survey.dto.RegisteredUserActivationDto;
import com.example.survey.dto.RegisteredUserChangePasswordDto;
import com.example.survey.dto.RegisteredUserDto;
import com.example.survey.dto.RegisteredUserRegistrationDto;
import com.example.survey.exception.ErrorResponse;
import com.example.survey.exception.LoginAlreadyInUseException;
import com.example.survey.exception.MailAlreadyInUseException;
import com.example.survey.exception.NoSuchRegisteredUserException;
import com.example.survey.service.RegisteredUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.survey.mapper.RegisteredUserDtoMapper.mapRegisteredUserToRegisteredUserDto;
import static com.example.survey.mapper.RegisteredUserDtoMapper.mapRegisteredUserToRegisteredUserDtoList;

@RestController
public class RegisteredUserController {
    private final RegisteredUserService registeredUserService;
    public RegisteredUserController(RegisteredUserService registeredUserService) {
        this.registeredUserService = registeredUserService;
    }

    @GetMapping("/users")
    public List<RegisteredUserDto> getUsers() {
        return mapRegisteredUserToRegisteredUserDtoList(registeredUserService.getRegisteredUsers());
    }

    @GetMapping("/users/{id}")
    public RegisteredUserDto getSingleUser(@PathVariable Long id) {
        return mapRegisteredUserToRegisteredUserDto(registeredUserService.getRegisteredUserById(id));
    }
    @PostMapping("/users")
    public RegisteredUserDto addUser(@RequestBody RegisteredUserRegistrationDto registeredUserRegistrationDto){
        return mapRegisteredUserToRegisteredUserDto(registeredUserService.addRegisteredUser(registeredUserRegistrationDto));
    }
    @PutMapping("/users/{id}")
    public RegisteredUserDto updateUser(@RequestBody RegisteredUserDto registeredUserDto){
        return mapRegisteredUserToRegisteredUserDto(registeredUserService.updateRegisteredUser(registeredUserDto));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        registeredUserService.deleteRegisteredUser(id);
    }

    @PutMapping("/users/{id}/activate")
    public void activateUser(@RequestBody RegisteredUserActivationDto registeredUserActivationDto){
        registeredUserService.activateRegisteredUser(registeredUserActivationDto);
    }

    @PutMapping("/users/{id}/password")
    public void changePassword(@RequestBody RegisteredUserChangePasswordDto registeredUserChangePasswordDto){
        registeredUserService.changePassword(registeredUserChangePasswordDto);
    }
}
