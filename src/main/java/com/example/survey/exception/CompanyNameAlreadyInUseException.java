package com.example.survey.exception;

public class CompanyNameAlreadyInUseException extends RuntimeException{
    private String message;

    public CompanyNameAlreadyInUseException() {}
    public CompanyNameAlreadyInUseException(String message){
        super(message);
        this.message = message;
    }
}
