package com.example.survey.service;

import com.example.survey.model.RegisteredUser;
import com.example.survey.repository.RegisteredUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor        //lombok feature
public class RegisteredUserService {
    private final RegisteredUserRepository registeredUserRepository;
    public List<RegisteredUser> getUsers() {
        return registeredUserRepository.findAll();
    }

    public RegisteredUser addUser(RegisteredUser registeredUser){
        return registeredUserRepository.save(registeredUser);
    }

    public RegisteredUser getSingleUser(Long id) {
        return registeredUserRepository.findById(id)
                .orElseThrow();                     //NoSuchElementException if no value found
    }
}
