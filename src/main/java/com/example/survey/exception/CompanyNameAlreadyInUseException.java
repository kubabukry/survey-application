package com.example.survey.exception;

public class CompanyNameAlreadyInUseException extends RuntimeException{

    public CompanyNameAlreadyInUseException(String message){
        super(message);
    }
}
