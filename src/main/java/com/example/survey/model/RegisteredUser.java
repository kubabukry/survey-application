package com.example.survey.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
