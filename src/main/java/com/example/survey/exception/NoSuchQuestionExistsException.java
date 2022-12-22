package com.example.survey.exception;

public class NoSuchQuestionExistsException extends RuntimeException{
    public NoSuchQuestionExistsException(String message){
        super(message);
    }
}
