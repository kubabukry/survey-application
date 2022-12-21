package com.example.survey.exception;

public class TitleAlreadyExistsException extends RuntimeException{
    private String message;

    public TitleAlreadyExistsException() {};
    public TitleAlreadyExistsException(String message){
        super(message);
        this.message = message;
    }
}
