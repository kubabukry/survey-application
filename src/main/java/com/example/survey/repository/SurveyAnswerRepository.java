package com.example.survey.repository;

import com.example.survey.model.CompanySurvey;
import com.example.survey.model.Question;
import com.example.survey.model.RegisteredUser;
import com.example.survey.model.SurveyAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyAnswerRepository extends JpaRepository<SurveyAnswer, Long> {
    Boolean existsByCompanySurveyAndIdUserAndQuestion(
            CompanySurvey companySurvey, RegisteredUser registeredUser, Question question);
}
