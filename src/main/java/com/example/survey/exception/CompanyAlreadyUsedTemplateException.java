package com.example.survey.exception;

public class CompanyAlreadyUsedTemplateException extends RuntimeException{

    public CompanyAlreadyUsedTemplateException(String message){
        super(message);
    }
}
