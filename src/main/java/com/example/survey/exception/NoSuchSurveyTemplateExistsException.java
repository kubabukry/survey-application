package com.example.survey.exception;

public class NoSuchSurveyTemplateExistsException extends RuntimeException{
    public NoSuchSurveyTemplateExistsException(String message){
        super(message);
    }
}
