package com.exception;

public class GameDoesNotExist extends ExceptionModel {
    public GameDoesNotExist(int errorCode,String message) {
        super(errorCode,message);
    }
}
