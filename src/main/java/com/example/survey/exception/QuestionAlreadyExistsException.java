package com.example.survey.exception;

public class QuestionAlreadyExistsException extends RuntimeException{
    private String message;

    public QuestionAlreadyExistsException() {}
    public QuestionAlreadyExistsException(String message){
        super(message);
        this.message = message;
    }
}
