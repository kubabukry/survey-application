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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "id_survey_template", referencedColumnName = "id")
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
