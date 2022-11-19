package com.example.survey.service;

import com.example.survey.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    private CategoryService categoryService;

    //getQuestion()
    //deleteQuestion()
    //updateQuestion()
    //createQuestion()
}
