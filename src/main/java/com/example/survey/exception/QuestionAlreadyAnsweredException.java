package com.example.survey.exception;

public class QuestionAlreadyAnsweredException extends RuntimeException{
    public QuestionAlreadyAnsweredException(String message){
        super(message);
    }
}
