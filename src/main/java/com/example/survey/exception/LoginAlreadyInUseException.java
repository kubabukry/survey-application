package com.example.survey.exception;

public class LoginAlreadyInUseException extends RuntimeException{
    public LoginAlreadyInUseException(String message){
        super(message);
    }
}
