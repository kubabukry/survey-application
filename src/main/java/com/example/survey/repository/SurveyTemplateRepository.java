package com.example.survey.repository;

import com.example.survey.model.SurveyTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyTemplateRepository extends JpaRepository<SurveyTemplate, Long> {
}
