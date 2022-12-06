package com.example.survey.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter     //lombok
@Setter     //lombok
@Table
@Entity                         //can't be named user as it's PostgreSQL reserved word
public class RegisteredUser {   //changed from User to RegisteredUser

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String name;

    private String mail;

    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRole", referencedColumnName = "id")
    @JsonBackReference
    private Role role;

    @OneToMany(mappedBy = "idUser")      //do sprawdzenia
    @JsonIgnore
    private List<SurveyAnswer> surveyAnswerList;

    @OneToOne(mappedBy = "idUser", optional = false)
    @PrimaryKeyJoinColumn
    @JsonManagedReference
    private Credentials credentials;
}
