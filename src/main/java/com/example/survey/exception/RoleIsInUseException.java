package com.example.survey.exception;

public class RoleIsInUseException extends RuntimeException{
    public RoleIsInUseException(String message){
        super(message);
    }
}
