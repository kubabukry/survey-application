package com.example.survey.exception;

public class NoSuchQuestionExistsException extends RuntimeException{
    private String message;

    public NoSuchQuestionExistsException() {}
    public NoSuchQuestionExistsException(String message){
        super(message);
        this.message = message;
    }
}
