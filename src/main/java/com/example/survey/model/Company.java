package com.example.survey.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //zmienione po sugestii snowflake
    private Long id;

    private String name;

    private Long nip;

    private String address;

    private Boolean isVerified;

    @OneToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private RegisteredUser idUser;

    @OneToMany(mappedBy = "company")
    private List<CompanySurvey> companySurveyList;
}
