package com.example.survey.exception;

public class MailAlreadyInUseException extends RuntimeException{
    public MailAlreadyInUseException(String message){
        super(message);
    }
}
