package com.example.survey.exception;

public class NoSuchSurveyTemplateExistsException extends RuntimeException{
    private String message;

    public NoSuchSurveyTemplateExistsException() {}
    public NoSuchSurveyTemplateExistsException(String message){
        super(message);
        this.message = message;
    }
}
