package com.example.survey.exception;

public class QuestionAlreadyExistsException extends RuntimeException{
    public QuestionAlreadyExistsException(String message){
        super(message);
    }
}
