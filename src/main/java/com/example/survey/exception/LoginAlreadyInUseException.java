package com.example.survey.exception;

public class LoginAlreadyInUseException extends RuntimeException{
    private String message;

    public LoginAlreadyInUseException() {}
    public LoginAlreadyInUseException(String message){
        super(message);
        this.message = message;
    }
}
