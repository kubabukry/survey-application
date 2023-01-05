package com.example.survey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter     //lombok
@Setter     //lombok
@Table
@Entity                         //can't be named user as it's PostgreSQL reserved word
public class RegisteredUser {   //changed from User to RegisteredUser

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String login;

    private String password;

    private String name;

    private String mail;

    private Boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER)                         //no Session error w przyapdku lazy przy autentykacji
    @JoinColumn(name = "idRole", referencedColumnName = "id")
    @JsonIgnore                          //blad bez JsonIgnore
    private Role role;

    @OneToMany(mappedBy = "idUser")      //do sprawdzenia
    @JsonIgnore
    private List<SurveyAnswer> surveyAnswerList;
}
