package com.example.survey.exception;

public class NoSuchCompanyExistsException extends RuntimeException{
    public NoSuchCompanyExistsException(String message){
        super(message);
    }
}
