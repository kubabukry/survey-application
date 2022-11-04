package com.example.survey.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter     //lombok
@Setter     //lombok
@Table
@Entity                         //can't be named user as it's PostgreSQL reserved word
public class RegisteredUser {   //changed from User to RegisteredUser

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;

    private String mail;

    private boolean isActive;   //boolean not Boolean because isActive will be
    @OneToOne(mappedBy = "idUser")
    private Company company;
}
