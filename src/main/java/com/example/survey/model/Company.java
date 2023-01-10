package com.example.survey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String nip;     //nip changed to String

    private String address;

    private Boolean isVerified;

    @OneToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private RegisteredUser idUser;

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<CompanySurvey> companySurveyList;
}
