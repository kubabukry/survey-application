package com.example.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.survey.model.Company;
import com.example.survey.model.CompanySurvey;
import com.example.survey.model.SurveyTemplate;

@Repository
public interface CompanySurveyRepository extends JpaRepository<CompanySurvey, Long> {
    Boolean existsByCompanyAndAndSurveyTemplate(Company company, SurveyTemplate surveyTemplate);
}
