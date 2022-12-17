package com.example.survey.repository;

import com.example.survey.model.CompanySurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanySurveyRepository extends JpaRepository<CompanySurvey, Long> {
}
