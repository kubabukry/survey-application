package com.example.survey.exception;

public class CategoryAlreadyExistsException extends RuntimeException{
    private String message;

    public CategoryAlreadyExistsException() {}
    public CategoryAlreadyExistsException(String message){
        super(message);
        this.message = message;
    }
}
