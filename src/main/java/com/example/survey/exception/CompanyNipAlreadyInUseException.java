package com.example.survey.exception;

public class CompanyNipAlreadyInUseException extends RuntimeException{
    public CompanyNipAlreadyInUseException(String message){
        super(message);
    }
}
