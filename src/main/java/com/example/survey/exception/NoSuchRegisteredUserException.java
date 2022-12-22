package com.example.survey.exception;

public class NoSuchRegisteredUserException extends RuntimeException{
    public NoSuchRegisteredUserException(String message){
        super(message);
    }
}
