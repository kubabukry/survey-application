package com.example.survey.controller;

import com.example.survey.model.RegisteredUser;
import com.example.survey.service.RegisteredUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RegisteredUserController {

    private final RegisteredUserService registeredUserService;
    @GetMapping("/users")
    public List<RegisteredUser> getUsers() {
        return registeredUserService.getUsers();
    }
    @PostMapping("/users")
    public RegisteredUser addUser(@RequestBody RegisteredUser registeredUser){
        return registeredUserService.addUser(registeredUser);
    }
}
