package com.example.survey.exception;

public class RoleIsInUseException extends RuntimeException{
    private String message;

    public RoleIsInUseException() {}
    public RoleIsInUseException(String message){
        super(message);
        this.message = message;
    }
}
