package com.example.survey.model;

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
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "questionList")     //do sprawdzenia
    private List<SurveyTemplate> surveyTemplateList;

    @OneToMany(mappedBy = "question")
    private List<SurveyAnswer> surveyAnswerList;
}
