package com.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExceptionModel extends  Exception{
    private int errorCode;
    private String errorMessage;

    public ExceptionModel(int errorCode, String errorMessage){
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
    }
}
