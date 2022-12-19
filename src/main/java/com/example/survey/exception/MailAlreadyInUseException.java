package com.example.survey.exception;

public class MailAlreadyInUseException extends RuntimeException{
    private String message;

    public MailAlreadyInUseException() {}
    public MailAlreadyInUseException(String message){
        super(message);
        this.message = message;
    }
}
