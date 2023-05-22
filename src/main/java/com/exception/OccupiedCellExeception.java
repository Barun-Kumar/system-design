package com.exception;

import lombok.Data;

@Data
public class OccupiedCellExeception extends ExceptionModel{
    public OccupiedCellExeception(int errorCode, String message){
        super(errorCode,message);
    }
}
