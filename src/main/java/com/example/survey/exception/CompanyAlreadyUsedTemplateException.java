package com.example.survey.exception;

public class CompanyAlreadyUsedTemplateException extends RuntimeException{
    private String message;

    public CompanyAlreadyUsedTemplateException() {}
    public CompanyAlreadyUsedTemplateException(String message){
        super(message);
        this.message = message;
    }
}
