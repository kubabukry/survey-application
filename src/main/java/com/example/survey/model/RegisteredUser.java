package com.example.survey.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Getter     //lombok
@Setter     //lombok
@Entity                         //can't be named user as it's PostgreSQL reserved word
public class RegisteredUser {   //changed from User to RegisteredUser

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;

    private String mail;

    private boolean isActive;   //boolean not Boolean because isActive will be
                                //predefined as false

    public RegisteredUser(){}

    public RegisteredUser(Long id, String login, String mail, boolean isActive) {
        this.id = id;
        this.login = login;
        this.mail = mail;
        this.isActive = isActive;
    }
}
