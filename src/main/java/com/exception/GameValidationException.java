package com.exception;

public class GameValidationException extends ExceptionModel {
    public GameValidationException(int errorCode, String message){
        super(errorCode,message);
    }
}
