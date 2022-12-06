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
    @GeneratedValue(strategy = GenerationType.AUTO) //zmienione po sugestii snowflake
    private Long id;

    private String name;

    private Long nip;

    private String address;

    private Boolean isVerified;

    @OneToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id", updatable = false, insertable = false)
    private RegisteredUser idUser;

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<CompanySurvey> companySurveyList;
}
