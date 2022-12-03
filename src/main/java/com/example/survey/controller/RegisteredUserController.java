package com.example.survey.controller;

import com.example.survey.model.RegisteredUser;
import com.example.survey.service.RegisteredUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegisteredUserController {
    private final RegisteredUserService registeredUserService;
    public RegisteredUserController(RegisteredUserService registeredUserService) {
        this.registeredUserService = registeredUserService;
    }

    @GetMapping("/users")
    public List<RegisteredUser> getUsers() {
        return registeredUserService.getUsers();
    }
    @GetMapping("/users/{id}")
    public RegisteredUser getSingleUser(@PathVariable Long id) {
        return registeredUserService.getSingleUser(id);
    }
    @PostMapping("/users")
    public RegisteredUser addUser(@RequestBody RegisteredUser registeredUser){
        return registeredUserService.addUser(registeredUser);
    }
}
