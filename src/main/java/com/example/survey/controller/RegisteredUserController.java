package com.example.survey.controller;

import com.example.survey.repository.RegisteredUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class RegisteredUserController {
    private final RegisteredUserRepository registeredUserRepository;

    public RegisteredUserController(RegisteredUserRepository registeredUserRepository) {
        this.registeredUserRepository = registeredUserRepository;
    }

    @GetMapping
    public ResponseEntity getAllUsers() {

        return ResponseEntity.ok(this.registeredUserRepository.findAll());
    }
}
