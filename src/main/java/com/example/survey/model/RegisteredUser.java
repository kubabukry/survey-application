package com.example.survey.model;

import com.sun.istack.NotNull;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String login;

    @NotNull
    private String name;

    @NotNull
    private String mail;

    @NotNull
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRole", referencedColumnName = "id")
    private Role role;

    @OneToMany(mappedBy = "idUser")      //do sprawdzenia
    private List<SurveyAnswer> surveyAnswerList;

    @OneToOne(mappedBy = "idUser", optional = false)
    @PrimaryKeyJoinColumn
    private Credentials credentials;
}
