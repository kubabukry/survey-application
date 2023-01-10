package com.example.survey.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
        if(loginExists && !registeredUser.getLogin().equals(registeredUserDto.login()))
            throw new LoginAlreadyInUseException(
                    "Login "+registeredUserDto.login()+" already in use");
        if(mailExists && !registeredUser.getMail().equals(registeredUserDto.mail()))
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

    //todo edit exception
    //po wygasnieciu refresh token user bedzie musial sie znowu zalogowac
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try{
                String token = authorizationHeader.substring("Bearer ".length());
                //todo use utility class instead
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String username = decodedJWT.getSubject();
                RegisteredUser registeredUser = registeredUserRepository.findByLogin(username);

                String refresh_token = JWT.create()
                        .withSubject(registeredUser.getLogin())
                        .withExpiresAt(new Date(System.currentTimeMillis()+ 10 * 60 * 1000))        //10 minut
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", registeredUser.getRole().getName())
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", refresh_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception e) {
                response.setHeader("Error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
//                    response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else
            throw new RuntimeException("Refresh token missing");
    }

    public RegisteredUser getUserDetails(String login) {
        Boolean existsByLogin = registeredUserRepository.existsByLogin(login);
        if(!existsByLogin)
            throw new NoSuchRegisteredUserException("No such registered user with login: "+login+" exists");
        RegisteredUser registeredUser = registeredUserRepository.findByLogin(login);
        return registeredUser;
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
