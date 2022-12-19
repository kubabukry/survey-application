package com.example.survey.exception;

public class NoSuchRoleExistsException extends RuntimeException{
    private String message;

    public NoSuchRoleExistsException() {};
    public NoSuchRoleExistsException(String message){
        super(message);
        this.message = message;
    }
}
