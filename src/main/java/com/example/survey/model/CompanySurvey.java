package com.example.survey.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanySurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_surveyTemplate", referencedColumnName = "id")
    @JsonBackReference
    private SurveyTemplate surveyTemplate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_company")
    @JsonBackReference
    private Company company;

    private Boolean isHidden;

    @OneToMany(mappedBy = "companySurvey")
    @JsonIgnore
    private List<SurveyAnswer> surveyAnswerList;
}
