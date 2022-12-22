package com.example.survey.exception;

public class RoleAlreadyExistsException extends RuntimeException{
    public RoleAlreadyExistsException(String message){
        super(message);
    }
}
