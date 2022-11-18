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
@Setter
@Getter
@Entity
@Table
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long nip;

    @NotNull
    private String address;

    @NotNull
    private Boolean isVerified;

    @OneToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private RegisteredUser idUser;

    @OneToMany(mappedBy = "company")
    private List<CompanySurvey> companySurveyList;
}
