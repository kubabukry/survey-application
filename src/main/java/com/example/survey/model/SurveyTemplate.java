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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategory")
    @JsonBackReference
    private Category category;

    @OneToMany(mappedBy = "surveyTemplate")
    @JsonManagedReference
    @JsonIgnore
    private List<CompanySurvey> companySurvey;

    @ManyToMany        //do sprawdzenia (w obu encjach lista, pol ManyToMany?)
    private List<Question> questionList;
}
