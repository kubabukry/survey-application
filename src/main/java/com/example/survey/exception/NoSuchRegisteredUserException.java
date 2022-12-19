package com.example.survey.exception;

public class NoSuchRegisteredUserException extends RuntimeException{
    private String message;

    public NoSuchRegisteredUserException() {}
    public NoSuchRegisteredUserException(String message){
        super(message);
        this.message = message;
    }
}
