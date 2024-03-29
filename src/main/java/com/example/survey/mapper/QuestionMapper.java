package com.example.survey.mapper;

import com.example.survey.dto.QuestionDto;
import com.example.survey.model.Question;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionMapper {

    public static QuestionDto mapQuestionToQuestionDto(Question question){
        return new QuestionDto(question.getId(), question.getName());
    }

    public static List<QuestionDto> mapQuestionListToQuestionDtoList(List<Question> questionList){
        return questionList.stream()
                .map(question -> new QuestionDto(
                        question.getId(),
                        question.getName()
                )).collect(Collectors.toList());
    }
}
