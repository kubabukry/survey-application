package com.example.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.survey.model.SurveyTemplate;

@Repository
public interface SurveyTemplateRepository extends JpaRepository<SurveyTemplate, Long> {
    Boolean existsByTitle(String title);
}
