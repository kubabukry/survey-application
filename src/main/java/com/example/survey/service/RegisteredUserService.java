package com.example.survey.service;

import com.example.survey.dto.RegisteredUserRegistrationDto;
import com.example.survey.model.Credentials;
import com.example.survey.model.RegisteredUser;
import com.example.survey.model.Role;
import com.example.survey.repository.CredentialsRepository;
import com.example.survey.repository.RegisteredUserRepository;
import com.example.survey.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RegisteredUserService {
    private final RegisteredUserRepository registeredUserRepository;

    private final CredentialsRepository credentialsRepository;

    private final RoleRepository roleRepository;

    public List<RegisteredUser> getUsers() {
        return registeredUserRepository.findAll();
    }

    public RegisteredUser addUser(RegisteredUserRegistrationDto registeredUserRegistrationDto){
        RegisteredUser registeredUser = new RegisteredUser();

        Role role = roleRepository.findDistinctByName("registered_user");
        Credentials credentials = new Credentials();

        registeredUser.setLogin(registeredUserRegistrationDto.login());
        registeredUser.setName(registeredUserRegistrationDto.name());
        registeredUser.setMail(registeredUserRegistrationDto.mail());
        registeredUser.setIsActive(false);
        registeredUser.setRole(role);
        //credentials.setPassword(registeredUserRegistrationDto.password());
        registeredUser.setCredentials(credentials);
        RegisteredUser savedUser = registeredUserRepository.save(registeredUser);


        return savedUser;
    }

    public RegisteredUser getSingleUser(Long id) {
        return registeredUserRepository.findById(id)
                .orElseThrow();                     //NoSuchElementException if no value found
    }


    //getRegisteredUser(): getSingleUser() +
    //updateRegisteredUser()
    //deleteRegisteredUser()
    //createRegisteredUser(): addUser() credentials trzeba zapisac przed przypisaniem
    //activateRegisteredUser()
    //changePassword()
    //login()
    //setCompany()
}
