package com.example.survey.exception;

public class NoSuchRoleExistsException extends RuntimeException{
    public NoSuchRoleExistsException(String message){
        super(message);
    }
}
