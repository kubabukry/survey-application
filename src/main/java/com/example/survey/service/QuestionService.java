package com.example.survey.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.survey.dto.QuestionCreationDto;
import com.example.survey.dto.QuestionDto;
import com.example.survey.exception.NoSuchQuestionExistsException;
import com.example.survey.exception.QuestionAlreadyExistsException;
import com.example.survey.model.Question;
import com.example.survey.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    private CategoryService categoryService;

    //todo czy question powinno miec kategorię? nie jest to uwzględnione w encji question
    public Question createQuestion(QuestionCreationDto questionCreationDto) {
        Boolean nameExists = questionRepository.existsByName(questionCreationDto.name());
        if(nameExists)
            throw new QuestionAlreadyExistsException(
                    "Question with name: "+questionCreationDto.name()+" already exists");

        Question question = new Question();
        question.setId(question.getId());
        question.setName(questionCreationDto.name());
        return questionRepository.save(question);
    }

    public Question updateQuestion(QuestionDto questionDto) {
        Question question = questionRepository.findById(questionDto.id())
                .orElseThrow(() -> new QuestionAlreadyExistsException(
                        "Question with id = "+questionDto.id()+" already exists"));
        Boolean nameExists = questionRepository.existsByName(questionDto.name());
        if(nameExists)
            throw new QuestionAlreadyExistsException("Question with name: "+questionDto.name()+" already exists");

        question.setName(questionDto.name());
        return questionRepository.save(question);
    }

    //todo jakies inne wyjatki potrzebne? czy  takie pytanie istnieje?
    public void deleteQuestion(Long id) {
        if(questionRepository.existsById(id))
            questionRepository.deleteById(id);
    }

    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    public Question getSingleQuestion(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new NoSuchQuestionExistsException(
                        "No such question with id = "+id+" exists"));
    }



    //getQuestion() +
    //deleteQuestion() +
    //updateQuestion() +
    //createQuestion() +

    //getQuestions() +
    //czy tu nie powinno być metody answerQuestion()?
}
