package com.example.survey.exception;

public class NoSuchCompanyExistsException extends RuntimeException{
    private String message;

    public NoSuchCompanyExistsException() {}
    public NoSuchCompanyExistsException(String message){
        super(message);
        this.message = message;
    }
}
