package com.example.survey.service;

import com.example.survey.dto.RegisteredUserActivationDto;
import com.example.survey.dto.RegisteredUserChangePasswordDto;
import com.example.survey.dto.RegisteredUserDto;
import com.example.survey.dto.RegisteredUserRegistrationDto;
import com.example.survey.exception.LoginAlreadyInUseException;
import com.example.survey.exception.MailAlreadyInUseException;
import com.example.survey.exception.NoSuchRegisteredUserException;
import com.example.survey.model.RegisteredUser;
import com.example.survey.model.Role;
import com.example.survey.repository.RegisteredUserRepository;
import com.example.survey.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisteredUserService implements UserDetailsService{
    private final RegisteredUserRepository registeredUserRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    public List<RegisteredUser> getRegisteredUsers() {
        return registeredUserRepository.findAll();
    }


    public RegisteredUser addRegisteredUser(RegisteredUserRegistrationDto registeredUserRegistrationDto){
        Boolean loginExists = registeredUserRepository.existsByLogin(registeredUserRegistrationDto.login());
        Boolean mailExists = registeredUserRepository.existsByMail(registeredUserRegistrationDto.mail());
        if(loginExists)
            throw new LoginAlreadyInUseException(
                    "Login "+registeredUserRegistrationDto.login()+" already in use");
        if(mailExists)
            throw new MailAlreadyInUseException(
                    "Mail "+registeredUserRegistrationDto.mail()+" already in use");

        RegisteredUser registeredUser = new RegisteredUser();
        Role role = roleRepository.findDistinctByName("registered_user");

        registeredUser.setLogin(registeredUserRegistrationDto.login());
        //todo haslo jest szyfrowane
        registeredUser.setPassword(passwordEncoder.encode(registeredUserRegistrationDto.password()));
        registeredUser.setName(registeredUserRegistrationDto.name());
        registeredUser.setMail(registeredUserRegistrationDto.mail());
        registeredUser.setIsActive(false);
        registeredUser.setRole(role);

        return registeredUserRepository.save(registeredUser);
    }


    public RegisteredUser getRegisteredUserById(Long id) {
        return registeredUserRepository.findById(id)
                .orElseThrow(() -> new NoSuchRegisteredUserException(
                        "No registered user present with id: "+id));
    }

    public RegisteredUser updateRegisteredUser(RegisteredUserDto registeredUserDto) {
        RegisteredUser registeredUser = registeredUserRepository.findById(registeredUserDto.id())
                .orElseThrow(() -> new NoSuchRegisteredUserException(
                        "No registered user present with id: "+registeredUserDto.id()
                ));

        Boolean loginExists = registeredUserRepository.existsByLogin(registeredUserDto.login());
        Boolean mailExists = registeredUserRepository.existsByMail(registeredUserDto.mail());
        if(loginExists)
            throw new LoginAlreadyInUseException(
                    "Login "+registeredUserDto.login()+" already in use");
        if(mailExists)
            throw new MailAlreadyInUseException(
                    "Mail "+registeredUserDto.mail()+" already in use");

        Role role = roleRepository.findDistinctByName(registeredUserDto.roleName());

        registeredUser.setId(registeredUserDto.id());
        registeredUser.setLogin(registeredUserDto.login());
        //todo zmiana hasla możliwa w update?
        registeredUser.setPassword(registeredUserRepository.findDistinctById(registeredUserDto.id()).getPassword());

        registeredUser.setName(registeredUserDto.name());
        registeredUser.setMail(registeredUserDto.mail());
        registeredUser.setIsActive(registeredUserDto.isActive());
        registeredUser.setRole(role);
        return registeredUserRepository.save(registeredUser);
    }

    //todo co jesli user ma utworzoną firmę? usuwamy też firmę? zostają dane z ankiet firmy?
    public void deleteRegisteredUser(Long id) {
        if(registeredUserRepository.existsById(id))
            registeredUserRepository.deleteById(id);
    }

    public void activateRegisteredUser(RegisteredUserActivationDto registeredUserActivationDto) {
        RegisteredUser registeredUser = registeredUserRepository.findById(registeredUserActivationDto.id())
                .orElseThrow(() -> new NoSuchRegisteredUserException(
                        "No registered user present with id: "+registeredUserActivationDto.id()
                ));

        registeredUser.setIsActive(registeredUserActivationDto.isActive());
        registeredUserRepository.save(registeredUser);
    }

    public void changePassword(RegisteredUserChangePasswordDto registeredUserChangePasswordDto) {
        RegisteredUser registeredUser = registeredUserRepository
                .findById(registeredUserChangePasswordDto.id())
                .orElseThrow(() -> new NoSuchRegisteredUserException(
                        "No registered user present with id: "+registeredUserChangePasswordDto.id()
                ));

        registeredUser.setPassword(registeredUserChangePasswordDto.password());
        registeredUserRepository.save(registeredUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws NoSuchRegisteredUserException {
        RegisteredUser registeredUser = registeredUserRepository.findByLogin(username);
        if(registeredUser==null)
            throw new NoSuchRegisteredUserException("No user with login "+username+" found");

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        String roleName = registeredUser.getRole().getName();
        authorities.add(new SimpleGrantedAuthority(roleName));

        return new User(registeredUser.getLogin(), registeredUser.getPassword(), authorities);
    }


    //getRegisteredUser(): getRegisteredUserById() +
    //updateRegisteredUser() +
    //deleteRegisteredUser() +
    //createRegisteredUser(): addRegisteredUser()+
    //activateRegisteredUser() +
    //changePassword() +
    //login()
    //setCompany()
}
