package com.example.survey.service;

import com.example.survey.dto.RegisteredUserActivationDto;
import com.example.survey.dto.RegisteredUserChangePasswordDto;
import com.example.survey.dto.RegisteredUserDto;
import com.example.survey.dto.RegisteredUserRegistrationDto;
import com.example.survey.model.RegisteredUser;
import com.example.survey.model.Role;
import com.example.survey.repository.RegisteredUserRepository;
import com.example.survey.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RegisteredUserService {
    private final RegisteredUserRepository registeredUserRepository;
    private final RoleRepository roleRepository;

    public List<RegisteredUser> getRegisteredUsers() {
        return registeredUserRepository.findAll();
    }

    public RegisteredUser addRegisteredUser(RegisteredUserRegistrationDto registeredUserRegistrationDto){
        RegisteredUser registeredUser = new RegisteredUser();
        Role role = roleRepository.findDistinctByName("registered_user");

        registeredUser.setLogin(registeredUserRegistrationDto.login());
        registeredUser.setPassword(registeredUserRegistrationDto.password());
        registeredUser.setName(registeredUserRegistrationDto.name());
        registeredUser.setMail(registeredUserRegistrationDto.mail());
        registeredUser.setIsActive(false);
        registeredUser.setRole(role);

        RegisteredUser savedUser = registeredUserRepository.save(registeredUser);

        return savedUser;
    }

    public RegisteredUser getSingleRegisteredUser(Long id) {
        return registeredUserRepository.findById(id)
                .orElseThrow();                             //dopisac obsluge reszty bledow
    }

    public RegisteredUser updateRegisteredUser(RegisteredUserDto registeredUserDto) {
        RegisteredUser registeredUser = new RegisteredUser();
        Role role = roleRepository.findDistinctByName(registeredUserDto.roleName());

        registeredUser.setId(registeredUserDto.id());
        registeredUser.setLogin(registeredUserDto.login());
        registeredUser.setPassword(registeredUserRepository.findDistinctById(registeredUserDto.id()).getPassword());

        registeredUser.setName(registeredUserDto.name());
        registeredUser.setMail(registeredUserDto.mail());
        registeredUser.setIsActive(registeredUserDto.isActive());
        registeredUser.setRole(role);

        return registeredUserRepository.save(registeredUser);
    }

    public void deleteRegisteredUser(Long id) {
        registeredUserRepository.deleteById(id);
    }

    public void activateRegisteredUser(RegisteredUserActivationDto registeredUserActivationDto) {
        RegisteredUser registeredUser = registeredUserRepository.findDistinctById(registeredUserActivationDto.id());
        registeredUser.setIsActive(registeredUserActivationDto.isActive());

        registeredUserRepository.save(registeredUser);

    }

    public void changePassword(RegisteredUserChangePasswordDto registeredUserChangePasswordDto) {
        RegisteredUser registeredUser = registeredUserRepository.findDistinctById(registeredUserChangePasswordDto.id());
        registeredUser.setPassword(registeredUserChangePasswordDto.password());

        registeredUserRepository.save(registeredUser);
    }


    //getRegisteredUser(): getSingleRegisteredUser() +
    //updateRegisteredUser() +
    //deleteRegisteredUser() +
    //createRegisteredUser(): addRegisteredUser()+
    //activateRegisteredUser() +
    //changePassword() +
    //login()
    //setCompany()

    //todo dodac validacje pol i obsluge bledow
}
