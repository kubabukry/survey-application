package com.example.survey.exception;

public class NoSuchCategoryExistsException extends RuntimeException{
    public NoSuchCategoryExistsException(String message){
        super(message);
    }
}
