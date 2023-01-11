package com.example.survey.controller;

import static com.example.survey.mapper.RegisteredUserDtoMapper.mapRegisteredUserToRegisteredUserDto;
import static com.example.survey.mapper.RegisteredUserDtoMapper.mapRegisteredUserToRegisteredUserDtoList;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.survey.dto.RegisteredUserActivationDto;
import com.example.survey.dto.RegisteredUserChangePasswordDto;
import com.example.survey.dto.RegisteredUserDto;
import com.example.survey.dto.RegisteredUserRegistrationDto;
import com.example.survey.service.RegisteredUserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
    public RegisteredUserDto addUser(@Valid @RequestBody RegisteredUserRegistrationDto registeredUserRegistrationDto){
        return mapRegisteredUserToRegisteredUserDto(registeredUserService.addRegisteredUser(registeredUserRegistrationDto));
    }
    @PutMapping("/users/{id}")
    public RegisteredUserDto updateUser(@Valid @RequestBody RegisteredUserDto registeredUserDto){
        return mapRegisteredUserToRegisteredUserDto(registeredUserService.updateRegisteredUser(registeredUserDto));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        registeredUserService.deleteRegisteredUser(id);
    }

    @PutMapping("/users/{id}/activate")
    public void activateUser(@Valid @RequestBody RegisteredUserActivationDto registeredUserActivationDto){
        registeredUserService.activateRegisteredUser(registeredUserActivationDto);
    }

    @PutMapping("/users/{id}/password")
    public void changePassword(@Valid @RequestBody RegisteredUserChangePasswordDto registeredUserChangePasswordDto){
        registeredUserService.changePassword(registeredUserChangePasswordDto);
    }

    @GetMapping("/users/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        registeredUserService.refreshToken(request, response);
    }

    @ApiOperation("Login.")
    @PostMapping("/login")
    public void fakeLogin(@ApiParam("User") @RequestParam String login, @ApiParam("Password") @RequestParam String password) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

    @ApiOperation("Logout.")
    @PostMapping("/logout")
    public void fakeLogout() {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

    @GetMapping("/users/details/{login}")
    public RegisteredUserDto getUserDetails(@PathVariable String login){
        return mapRegisteredUserToRegisteredUserDto(registeredUserService.getUserDetails(login));
    }
}
