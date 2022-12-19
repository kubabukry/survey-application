package com.example.survey.exception;

public class NoSuchCategoryExistsException extends RuntimeException{
    private String message;

    public NoSuchCategoryExistsException() {}
    public NoSuchCategoryExistsException(String message){
        super(message);
        this.message = message;
    }
}
