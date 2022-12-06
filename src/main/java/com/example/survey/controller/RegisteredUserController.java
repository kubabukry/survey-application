package com.example.survey.controller;

import com.example.survey.dto.RegisteredUserDto;
import com.example.survey.model.RegisteredUser;
import com.example.survey.service.RegisteredUserService;
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
        return mapRegisteredUserToRegisteredUserDtoList(registeredUserService.getUsers());
    }

    @GetMapping("/users/{id}")
    public RegisteredUserDto getSingleUser(@PathVariable Long id) {
        return mapRegisteredUserToRegisteredUserDto(registeredUserService.getSingleUser(id));
    }
    @PostMapping("/users")
    public RegisteredUser addUser(@RequestBody RegisteredUser registeredUser){
        return registeredUserService.addUser(registeredUser);
    }
}
