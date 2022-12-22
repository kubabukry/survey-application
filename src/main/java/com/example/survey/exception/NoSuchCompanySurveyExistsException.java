package com.example.survey.exception;

public class NoSuchCompanySurveyExistsException extends RuntimeException{
    public NoSuchCompanySurveyExistsException(String message) {
        super(message);
    }
}
