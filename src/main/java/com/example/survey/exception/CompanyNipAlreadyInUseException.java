package com.example.survey.exception;

public class CompanyNipAlreadyInUseException extends RuntimeException{
    private String message;

    public CompanyNipAlreadyInUseException() {}
    public CompanyNipAlreadyInUseException(String message){
        super(message);
        this.message = message;
    }
}
